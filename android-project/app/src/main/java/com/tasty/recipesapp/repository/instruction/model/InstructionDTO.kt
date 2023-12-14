package com.tasty.recipesapp.repository.instruction.model

import com.google.gson.annotations.SerializedName

data class InstructionDTO(
    @SerializedName("position") var position: Int? = null,
    @SerializedName("display_text") var displayText: String? = null,
    @SerializedName("start_time") var startTime: Int? = null,
    @SerializedName("appliance") var appliance: String? = null,
    @SerializedName("end_time") var endTime: Int? = null,
    @SerializedName("temperature") var temperature: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("recipeId") var recipeId: Int? = null
)

fun InstructionDTO.toModel(recipeId: Int): InstructionModel? {
    return this.id?.let {
        this.displayText?.let { it1 ->
            this.startTime?.let { it2 ->
                this.endTime?.let { it3 ->
                    InstructionTime(
                        start_time = it2,
                        end_time = it3
                    )
                }
            }?.let { it3 ->
                InstructionModel(
                    id = it,
                    display_text = it1,
                    time = it3,
                    recipeId = recipeId
                )
            }
        }
    }
}

fun List<InstructionDTO>.toModelList(recipeId: Int): List<InstructionModel> =
    this.mapNotNull { it.toModel(recipeId) }
