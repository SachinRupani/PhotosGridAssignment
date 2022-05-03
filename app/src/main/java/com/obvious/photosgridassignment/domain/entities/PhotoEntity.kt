package com.obvious.photosgridassignment.domain.entities

/**
 * Entity holding the photo detail
 */
data class PhotoEntity(
    val title: String?,
    val copyright: String?,
    val strDate: String,
    val explanation: String?,
    val hdImageUrl: String,
    val thumbnailImageUrl: String,
    val mediaType: String?,
    val serviceVersion: String?
)