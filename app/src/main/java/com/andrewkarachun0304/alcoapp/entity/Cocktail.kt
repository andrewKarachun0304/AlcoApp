package com.andrewkarachun0304.alcoapp.entity

data class Cocktail (
    val id: Int,
    val name: String,
    val type: String,
    val category: String,
    val imageUrl: String,
    var state: Boolean = false
)