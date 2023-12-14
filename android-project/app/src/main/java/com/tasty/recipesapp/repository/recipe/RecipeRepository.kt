package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.repository.instruction.model.InstructionModel
import com.tasty.recipesapp.repository.instruction.model.toModelList
import com.tasty.recipesapp.repository.recipe.entity.RecipeDao
import com.tasty.recipesapp.repository.recipe.entity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeDTO
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModel
import com.tasty.recipesapp.repository.recipe.model.toModelList
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class RecipeRepository(private val recipeDao: RecipeDao) {

    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList = ArrayList<RecipeModel>()
    private var instrucionsList = emptyList<InstructionModel>()
    private var myRecipeList = ArrayList<RecipeModel>()
    private lateinit var jsonString: String


    //Database
    suspend fun getMyRecipe(recipeId: Int): RecipeModel?{
        var recipeResult : RecipeModel? = null
        try {
            val result = recipeDao.getRecipeById(recipeId)
            val jsonObject = JSONObject( result!!.json)
            jsonObject.apply { put("id", result.internalId) }
            recipeResult = Gson().fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }catch (e: JSONException){
            Log.e("FATAL","Empty result")
        }
        return  recipeResult
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun deleteDbRecipe(recipe: RecipeModel) {
        recipeDao.deleteRecipeById(recipe.id)
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().mapNotNull {
            if (it.json.isNotEmpty()) {
                try {
                    val jsonObject = JSONObject(it.json)
                    jsonObject.apply { put("id", it.internalId) }
                    Gson().fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
                } catch (e: JSONException) {
                    null
                }
            } else {
                null
            }

        }
    }

    suspend fun getMaxId(): Int {
        return recipeDao.getMaxId()
    }


    //Json file
    fun getRecipes(context: Context): List<RecipeModel> {

        try {

            jsonString =
                context.assets.open("all_recipes.json")
                    .bufferedReader()
                    .use {
                        it.readText()
                    }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error at reading JSON file: $ioException")
        }
        val recipesResponse: RecipesDTO =
            Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)
        recipesList = recipesResponse.results.toModelList() as ArrayList<RecipeModel>

        return recipesList
    }

    fun getUpdatedRecipes(): List<RecipeModel> {
        return  recipesList
    }

    fun getRecipeInstructions(context: Context, recipeId: Int): List<InstructionModel> {
        try {

            jsonString =
                context.assets.open("all_recipes.json")
                    .bufferedReader()
                    .use {
                        it.readText()
                    }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error at reading JSON file: $ioException")
        }
        val recipesResponse: RecipesDTO =
            Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)
        instrucionsList =
            recipesResponse.results.flatMap { it.instructions.orEmpty() }?.toModelList(recipeId)
                ?: emptyList()
        Log.d(TAG, "$instrucionsList")
        instrucionsList.filter { it.recipeId == recipeId }
        return instrucionsList
    }


    fun getRecipe(recipeId: Int): RecipeModel? {
        return recipesList.find { it.id == recipeId }
    }


    fun insertRecipe(recipeModel: RecipeModel): Boolean {
        return recipesList.add(recipeModel)
    }

    fun deleteRecipe(recipeId: RecipeModel): Boolean {
        return recipesList.remove(recipeId)
    }

}