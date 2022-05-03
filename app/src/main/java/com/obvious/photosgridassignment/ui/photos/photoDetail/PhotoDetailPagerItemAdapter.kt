package com.obvious.photosgridassignment.ui.photos.photoDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.application.utils.toDisplayDate
import com.obvious.photosgridassignment.databinding.PagerItemPhotoDetailBinding
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.ui.photos.PhotoEntityDiffCallback

class PhotoDetailPagerItemAdapter(
    diffCallback: PhotoEntityDiffCallback = PhotoEntityDiffCallback
) : ListAdapter<PhotoEntity, PhotoDetailPagerItemAdapter.PhotoDetailViewHolder>(diffCallback) {

    class PhotoDetailViewHolder(private val binding: PagerItemPhotoDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(photoEntity: PhotoEntity?) {
            photoEntity?.run {

                //Place image into the image view
                binding.imgPhotoDetail.load(thumbnailImageUrl)

                //Place Title Text
                binding.txtTitle.text = title

                //Place Date
                binding.txtDate.text = strDate.toDisplayDate()

                //Place Photo Explanation
                binding.txtExplanation.text = explanation

                //Place Photo Copyright Info
                val shouldShowCopyrightSection = copyright != null
                binding.cardDetailsCopyright.isVisible = shouldShowCopyrightSection
                if(shouldShowCopyrightSection) {
                    binding.txtCopyright.text = copyright
                }


            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoDetailViewHolder {
        val itemBinding =
            PagerItemPhotoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoDetailViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoDetailViewHolder, position: Int) {
        holder.bindTo(photoEntity = getItem(position))
    }
}