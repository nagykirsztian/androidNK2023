package com.tasty.recipesapp.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {
    var recipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()


    fun fetchRecipeData(context: Context) {
        recipeList.value = repository.getRecipes(context)
    }

    fun deleteRecipe(recipeId: RecipeModel){
        repository.deleteRecipe(recipeId)
        recipeList.value = repository.getUpdatedRecipes()
    }

}
