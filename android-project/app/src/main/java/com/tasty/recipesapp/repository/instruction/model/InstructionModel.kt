package com.tasty.recipesapp.repository.instruction.model

data class InstructionModel(
    val id: Int?,
    val display_text: String?,
    val time: InstructionTime?,
    val recipeId: Int
)

data class InstructionTime(
    val start_time: Int?,
    val end_time: Int?
)


