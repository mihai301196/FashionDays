package com.mihaiim.fashiondays.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mihaiim.fashiondays.databinding.ItemClothingBinding
import com.mihaiim.fashiondays.databinding.ItemTitleBinding
import com.mihaiim.fashiondays.domain.models.ClothingItemModel
import com.mihaiim.fashiondays.domain.models.ClothingModel
import com.mihaiim.fashiondays.domain.models.TitleModel

class ClothingAdapter(
    private val glide: RequestManager,
    private val onLongClick: (Long) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_TITLE = 0
        private const val TYPE_CLOTHING = 1
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ClothingItemModel>() {

        override fun areItemsTheSame(
            oldItem: ClothingItemModel,
            newItem: ClothingItemModel,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ClothingItemModel,
            newItem: ClothingItemModel,
        ) = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_TITLE) {
            TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
        } else {
            ClothingViewHolder(
                ItemClothingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> holder.bind(differ.currentList[position] as TitleModel)
            is ClothingViewHolder -> holder.bind(differ.currentList[position] as ClothingModel)
        }
    }

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position] is ClothingModel) TYPE_CLOTHING
            else TYPE_TITLE
    }

    fun submitList(list: List<ClothingItemModel>) = differ.submitList(list)

    inner class ClothingViewHolder(private val binding: ItemClothingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ClothingModel) {
            binding.root.setOnLongClickListener {
                onLongClick(item.productId)
                false
            }
            glide.load(item.productImages.thumbs[0]).into(binding.ivPicture)
            binding.tvName.text = item.productName
            binding.tvBrand.text = item.productBrand
        }
    }

    inner class TitleViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TitleModel) {
            binding.textView.text = item.name
        }
    }
}