package shvyn22.animesearch.ui.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.databinding.ItemBookmarkBinding
import shvyn22.animesearch.util.defaultRequests

class BookmarksAdapter(
    private val onNavigateToAnilist: (Int) -> Unit
) : ListAdapter<Bookmark, BookmarksAdapter.BookmarksViewHolder>(bookmarksDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        return BookmarksViewHolder(
            ItemBookmarkBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { holder.bind(it) }
    }

    inner class BookmarksViewHolder(
        private val binding: ItemBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Bookmark) {
            binding.apply {
                tvTitle.text = item.title

                ivIsAdult.setImageResource(
                    if (item.isAdult) R.drawable.ic_adult
                    else R.drawable.ic_not_adult
                )

                Glide.with(itemView)
                    .load(item.image)
                    .defaultRequests()
                    .into(ivScreen)

                ivAnilist.setOnClickListener {
                    onNavigateToAnilist(item.id)
                }
            }
        }
    }

    companion object {
        private val bookmarksDiffUtil = object : DiffUtil.ItemCallback<Bookmark>() {
            override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean =
                oldItem == newItem
        }
    }
}