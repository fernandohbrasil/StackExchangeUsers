package com.fernandohbrasil.stackexchange.network.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "user_id") val id: Int,
    @field:Json(name = "display_name") val name: String,
    @field:Json(name = "badge_counts") val badges: Badges,
    @field:Json(name = "creation_date") val creationDate: Long,
    @field:Json(name = "location") val location: String,
    @field:Json(name = "profile_image") val profileImageUrl: String,
    @field:Json(name = "reputation") val reputation: Int
)

data class Users(
    val items: List<User>
)