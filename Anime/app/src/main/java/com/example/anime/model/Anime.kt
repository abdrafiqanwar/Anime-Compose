package com.example.anime.model

data class Anime(
    val id: Long,
    val title: String,
    val type: String,
    val image: Int,
    val rating: String,
    val synopsis: String
)