package com.obvious.photosgridassignment.domain.common

data class GeneralException(
    val message: String,
    val exception: Exception?
)
