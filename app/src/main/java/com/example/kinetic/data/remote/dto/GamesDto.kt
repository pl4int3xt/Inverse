package com.example.kinetic.data.remote.dto

data class GamesDto(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any,
    val results: List<Result>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)
