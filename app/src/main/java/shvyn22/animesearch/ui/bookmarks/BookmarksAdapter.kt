package shvyn22.animesearch.ui.bookmarks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.databinding.ItemBookmarkBinding
import shvyn22.animesearch.util.defaultRequests

class BookmarksAdapter(
    private val onNavigateToAnilist: (Int) -> Unit
) : RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder>() {

    private val items = mutableListOf<AnimeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        return BookmarksViewHolder(
            ItemBookmarkBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val item = items.getOrNull(position)

        item?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAndNotify(newItems: List<AnimeModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class BookmarksViewHolder(
        private val binding: ItemBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimeModel) {
            binding.apply {
                tvTitle.text = item.title

                ivIsAdult.setImageResource(
                    if (item.isAdult) R.drawable.ic_adult
                    else R.drawable.ic_not_adult
                )

                Glide.with(root)
                    .load(item.image)
                    .defaultRequests()
                    .into(ivScreen)

                ivAnilist.setOnClickListener {
                    onNavigateToAnilist(item.id)
                }
            }
        }
    }
}