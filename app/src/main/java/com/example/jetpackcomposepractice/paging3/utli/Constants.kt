package com.example.jetpackcomposepractice.paging3.utli

import com.example.jetpackcomposepractice.BuildConfig

object Constants {

    const val UNSPLASH_DATABASE = "unsplash_database"
    const val UNSPLASH_IMAGE_TABLE = "unsplash_image_table"
    const val UNSPLASH_REMOTE_KEYS_TABLE = "unsplash_remote_keys_table"
    const val API_CONFIG = "Authorization: Client-ID ${BuildConfig.API_KEY}"
    const val UNSPLASH_BASE_URL = "https://api.unsplash.com"


    const val ITEMS_PER_PAGE = 10
}
