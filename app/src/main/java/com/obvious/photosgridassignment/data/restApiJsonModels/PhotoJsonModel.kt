package com.obvious.photosgridassignment.data.restApiJsonModels

import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Class holding the photo details
 * coming from the JSON response
 */
@JsonClass(generateAdapter = true)
data class PhotoJsonModel(
    val title: String?,
    val copyright: String?,
    val date: String?, //Format => 2019-12-01 (YYYY-MM-DD)
    val explanation: String?,
    @Json(name = "hdurl") val hdImageUrl: String?,
    @Json(name = "url") val thumbnailImageUrl: String?,
    @Json(name = "media_type") val mediaType: String?, //image
    @Json(name = "service_version") val serviceVersion: String?
) {

    /**
     * Function to convert the API Response Json
     * to Domain Entity
     * @return [PhotoEntity]
     */
    fun toPhotoEntity(): PhotoEntity {
        return PhotoEntity(
            title = title,
            copyright = copyright ?: "",
            strDate = date ?: "1971-01-01",
            explanation = explanation,
            hdImageUrl = hdImageUrl ?: thumbnailImageUrl ?: "",
            thumbnailImageUrl = thumbnailImageUrl ?: "",
            mediaType = mediaType,
            serviceVersion = serviceVersion
        )
    }
}