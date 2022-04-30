package com.obvious.photosgridassignment.ui.photos.photoGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.databinding.RowItemPhotoBinding
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.ui.photos.PhotoEntityDiffCallback

/**
 * Photo Grid Adapter for holding the UI of grid items/photos
 */
class PhotoGridAdapter(
    private val onItemClick: (photoEntity: PhotoEntity) -> Unit,
    diffCallback: PhotoEntityDiffCallback = PhotoEntityDiffCallback
) : ListAdapter<PhotoEntity, PhotoGridAdapter.PhotoGridViewHolder>(diffCallback) {

    /**
     * ViewHolder class for holding row item view
     * [RowItemPhotoBinding]
     */
    class PhotoGridViewHolder(private val binding: RowItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(photoEntity: PhotoEntity?, onItemClick: (photoEntity: PhotoEntity) -> Unit) {
            //Access photo entity model
            photoEntity?.run {

                //Place image into the image view
                binding.imgPhoto.load(thumbnailImageUrl) {
                    placeholder(R.drawable.img_placeholder)
                }

                //Click handler on the image view
                binding.imgPhoto.setOnClickListener {
                    onItemClick.invoke(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        val itemBinding =
            RowItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoGridViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.bindTo(
            photoEntity = getItem(position),
            onItemClick = onItemClick
        )
    }

}