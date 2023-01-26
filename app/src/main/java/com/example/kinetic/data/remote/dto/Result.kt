package com.example.kinetic.data.remote.dto

import com.example.kinetic.data.remote.dto.Result
import com.example.kinetic.domain.model.GameModel

data class Result(
    val added: Int,
    val added_by_status: AddedByStatusX,
    val background_image: String,
    val clip: Any,
    val dominant_color: String,
    val esrb_rating: EsrbRatingX,
    val genres: List<GenreX>,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val parent_platforms: List<ParentPlatformX>,
    val platforms: List<PlatformXXXXX>,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<RatingX>,
    val ratings_count: Int,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val saturated_color: String,
    val short_screenshots: List<ShortScreenshot>,
    val slug: String,
    val stores: List<StoreXX>,
    val suggestions_count: Int,
    val tags: List<TagX>,
    val tba: Boolean,
    val updated: String,
    val user_game: Any
)

fun Result.toGameModel(): GameModel{
    return GameModel(
        id = id,
        name = name,
        image = background_image,
        rating = rating
    )
}