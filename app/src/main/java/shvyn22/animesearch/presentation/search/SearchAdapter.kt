package shvyn22.animesearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.animesearch.R
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.databinding.ItemResultBinding
import shvyn22.animesearch.util.defaultRequests

class SearchAdapter(
    private val onNavigateToAnilist: (Int) -> Unit,
    private val onAddToBookmarks: (AnimeModel) -> Unit,
    private val onRemoveFromBookmarks: (Int) -> Unit,
) : ListAdapter<AnimeModel, SearchAdapter.SearchViewHolder>(searchDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { holder.bind(item) }
    }

    inner class SearchViewHolder(
        private val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimeModel) {
            binding.apply {
                tvSimilarity.text = itemView.context.getString(
                    R.string.text_similarity, item.similarity
                )
                tvSimilarity.setBackgroundColor(
                    when (item.similarity) {
                        in 0..75 ->
                            itemView.resources.getColor(R.color.red, itemView.context.theme)
                        in 0..90 ->
                            itemView.resources.getColor(R.color.yellow, itemView.context.theme)
                        else ->
                            itemView.resources.getColor(R.color.green_400, itemView.context.theme)
                    }
                )

                tvEpisode.text = itemView.context.getString(
                    R.string.text_episode, item.episode
                )

                val (from, to) = item.range
                tvRange.text = itemView.context.getString(
                    R.string.text_range, from, to
                )

                tvTitle.text = itemView.context.getString(
                    R.string.text_title, item.title
                )

                ivIsAdult.setImageResource(
                    if (item.isAdult) R.drawable.ic_adult
                    else R.drawable.ic_not_adult
                )

                Glide.with(itemView)
                    .load(item.image)
                    .defaultRequests()
                    .into(ivScreen)

                ivAddBookmark.setOnClickListener {
                    if (item.isBookmarked) onRemoveFromBookmarks(item.id)
                    else onAddToBookmarks(item)
                }

                ivAddBookmark.setImageResource(
                    if (item.isBookmarked) R.drawable.ic_favorite
                    else R.drawable.ic_not_favorite
                )

                ivAnilist.setOnClickListener { onNavigateToAnilist(item.id) }
            }
        }
    }

    companion object {
        private val searchDiffUtil = object : DiffUtil.ItemCallback<AnimeModel>() {
            override fun areItemsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean =
                oldItem == newItem
        }
    }
}