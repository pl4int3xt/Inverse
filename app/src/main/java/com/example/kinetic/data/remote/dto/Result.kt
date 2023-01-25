package com.example.kinetic.data.remote.dto

import com.example.kinetic.domain.model.GameModel
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val added: Int ? = 0,
    val added_by_status: AddedByStatus? = AddedByStatus(),
    val background_image: String? = "",
    val clip: String? = "",
    val dominant_color: String? = "",
    val esrb_rating: EsrbRating,
    val genres: List<Genre>? = emptyList(),
    val id: Int,
    val metacritic: Int? = 0,
    val name: String? = "",
    val parent_platforms: List<ParentPlatform>? = emptyList(),
    val platforms: List<PlatformX>? = emptyList(),
    val playtime: Int? = 0,
    val rating: Double? = 0.0,
    val rating_top: Int? = 0,
    val ratings: List<Rating>,
    val ratings_count: Int? = 0,
    val released: String? = "",
    val reviews_count: Int? = 0,
    val reviews_text_count: Int? = 0,
    val saturated_color: String? = "",
    val short_screenshots: List<ShortScreenshot>? = emptyList(),
    val slug: String? = "",
    val stores: List<Store>? = emptyList(),
    val suggestions_count: Int? = 0,
    val tags: List<Tag>? = emptyList(),
    val tba: Boolean? = false,
    val updated: String? = "",
    val user_game: String? = "",
)

fun Result.toGameModel(): GameModel{
    return GameModel(
        id = id,
        name = name?:"",
        image = background_image?:"",
        rating = rating?:0.0,
    )
}