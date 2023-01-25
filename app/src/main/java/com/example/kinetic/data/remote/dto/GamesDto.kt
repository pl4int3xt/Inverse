package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesDto(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String? = "",
    val nofollow: Boolean? = false,
    val nofollow_collections: List<String>? = emptyList(),
    val noindex: Boolean? = false,
    val previous: String? = "",
    val results: List<Result>,
    val seo_description: String? = "",
    val seo_h1: String? = "",
    val seo_keywords: String? = "",
    val seo_title: String? = ""
)