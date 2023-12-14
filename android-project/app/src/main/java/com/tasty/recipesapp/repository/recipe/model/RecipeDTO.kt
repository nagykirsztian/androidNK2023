package com.tasty.recipesapp.repository.recipe.model

import com.tasty.recipesapp.repository.instruction.model.InstructionDTO

data class RecipeDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail_url: String,
    val instructions: List<InstructionDTO>?

)

data class RecipeData(
    val name: String,
    val description: String,
    val pictureUrl: String,
    val videoUrl: String
)

fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnail_url
    )
}

fun List<RecipeDTO>.toModelList(): List<RecipeModel> =
    this.map { it.toModel() }
