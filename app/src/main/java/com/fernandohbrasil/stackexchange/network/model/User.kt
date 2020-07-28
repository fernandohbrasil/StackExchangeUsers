package com.fernandohbrasil.stackexchange.network.model

data class User(
    val user_id: Int,
    val display_name: String,
    val badge_counts: BadgeCounts,
    val creation_date: Int,
    val location: String,
    val profile_image: String,
    val reputation: Int
)

data class Users(
    val items: List<User>
)