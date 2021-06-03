package com.grinaldi.movieworld.core.utils

object ImageUrl {
    fun getThumbnailImageUrl(name: String): String = "${Constant.THUMBNAIL_IMAGE_URL}$name"

    fun getOriginalImageUrl(name: String): String = "${Constant.FULL_SIZE_IMAGE_URL}$name"
}