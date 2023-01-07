package com.mihaiim.fashiondays.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mihaiim.fashiondays.databinding.ItemClothingBinding
import com.mihaiim.fashiondays.domain.models.ClothingModel

class ClothingAdapter(
    private val glide: RequestManager,
    private val onLongClick: (Long) -> Unit,
) : RecyclerView.Adapter<ClothingAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ClothingModel>() {

        override fun areItemsTheSame(
            oldItem: ClothingModel,
            newItem: ClothingModel,
        ) = oldItem.productId == newItem.productId

        override fun areContentsTheSame(
            oldItem: ClothingModel,
            newItem: ClothingModel,
        ) = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClothingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun submitList(list: List<ClothingModel>) = differ.submitList(list)

    inner class ViewHolder(private val binding: ItemClothingBinding) :
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
}