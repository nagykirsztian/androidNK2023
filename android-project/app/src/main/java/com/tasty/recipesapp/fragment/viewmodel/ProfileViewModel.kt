package com.tasty.recipesapp.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.entity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    fun getAllRecipes() = viewModelScope.launch { _recipes.value = repository.getAllRecipes() }

//    fun getMaxId() = viewModelScope.launch { recipeId = repository.getMaxId() }


    fun insertRecipe(recipe: RecipeEntity): LiveData<Result<String>> {
        val result = performInsertion(recipe)
        return MutableLiveData(result)
    }

    private fun performInsertion(recipe: RecipeEntity): Result<String> {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
            _recipes.value = repository.getAllRecipes()
        }
        return Result.Success("Insertion successful")
    }

    fun removeRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            repository.deleteDbRecipe(recipe)
            val updatedRecipes = repository.getAllRecipes()
            _recipes.value = updatedRecipes
        }
    }
}