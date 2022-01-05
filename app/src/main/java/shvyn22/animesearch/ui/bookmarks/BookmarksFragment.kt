package shvyn22.animesearch.ui.bookmarks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.animesearch.R
import shvyn22.animesearch.databinding.FragmentBookmarksBinding
import shvyn22.animesearch.util.*

@AndroidEntryPoint
class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksViewModel by viewModels()

    private fun initUI(
        binding: FragmentBookmarksBinding,
        adapter: BookmarksAdapter
    ) {
        binding.apply {
            rvBookmarks.apply {
                this.adapter = adapter

                ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(
                        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    ) {
                        override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                        ) = false

                        override fun onSwiped(
                            viewHolder: RecyclerView.ViewHolder,
                            direction: Int
                        ) {
                            viewModel.onRemoveFromBookmarks(
                                adapter.currentList[viewHolder.adapterPosition].id
                            )
                        }
                    }
                ).attachToRecyclerView(this)
            }
        }
    }

    private fun subscribeToObservers(
        binding: FragmentBookmarksBinding,
        adapter: BookmarksAdapter,
    ) {
        binding.apply {
            viewModel.getBookmarks().collectOnLifecycle(viewLifecycleOwner) {
                pbLoading.isVisible = it is Resource.Loading

                if (it is Resource.Success)
                    adapter.submitList(it.data)
                else if (it is Resource.Error)
                    viewModel.onErrorOccurred(it.error)
            }

            viewModel.events.collectOnLifecycle(viewLifecycleOwner) {
                when (it) {
                    is StateEvent.NavigateToAnilist -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ANILIST_URL + it.id))
                        startActivity(intent)
                    }
                    is StateEvent.ShowError ->
                        when (it.error) {
                            is ErrorType.Fetching -> root.showError(getString(R.string.text_error_fetching))
                            is ErrorType.Specified -> root.showError(it.error.msg)
                            is ErrorType.NoBookmarks -> {
                                adapter.currentList.clear()
                                tvBookmarksHint.text = getString(R.string.text_error_no_bookmarks)
                            }
                        }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBookmarksBinding.bind(view)
        val adapter = BookmarksAdapter(
            onNavigateToAnilist = viewModel::onNavigateToAnilist
        )

        initUI(binding, adapter)
        subscribeToObservers(binding, adapter)

        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete)
            viewModel.onRemoveAllFromBookmarks()
        return super.onOptionsItemSelected(item)
    }
}