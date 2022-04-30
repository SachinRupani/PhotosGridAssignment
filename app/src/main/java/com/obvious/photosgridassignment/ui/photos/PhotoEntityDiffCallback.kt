package com.obvious.photosgridassignment.ui.photos

import androidx.recyclerview.widget.DiffUtil
import com.obvious.photosgridassignment.domain.entities.PhotoEntity

/**
 * Diff Callback for photos grid
 */
object PhotoEntityDiffCallback : DiffUtil.ItemCallback<PhotoEntity>() {
    override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
        return oldItem == newItem
    }
}