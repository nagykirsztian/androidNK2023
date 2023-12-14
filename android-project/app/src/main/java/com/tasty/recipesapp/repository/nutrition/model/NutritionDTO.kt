package com.tasty.recipesapp.repository.nutrition.model
import com.google.gson.annotations.SerializedName

data class NutritionDTO(@SerializedName("fiber"         ) var fiber         : Int?    = null,
                        @SerializedName("updated_at"    ) var updatedAt     : String? = null,
                        @SerializedName("protein"       ) var protein       : Int?    = null,
                        @SerializedName("fat"           ) var fat           : Int?    = null,
                        @SerializedName("calories"      ) var calories      : Int?    = null,
                        @SerializedName("sugar"         ) var sugar         : Int?    = null,
                        @SerializedName("carbohydrates" ) var carbohydrates : Int?    = null
)
