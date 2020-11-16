package com.andrewkarachun0304.alcoapp.mappers

import com.andrewkarachun0304.alcoapp.entity.Cocktail
import com.andrewkarachun0304.alcoapp.dto.CocktailsResponse

object CocktailMapper {
    fun map(from: CocktailsResponse.Drink?) = Cocktail(
        id = from?.idDrink ?: 0,
        name = from?.strDrink.orEmpty(),
        type = from?.strAlcoholic.orEmpty(),
        category = from?.strCategory.orEmpty(),
        imageUrl = from?.strDrinkThumb.orEmpty()
    )
}