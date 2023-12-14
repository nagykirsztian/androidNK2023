package com.tasty.recipesapp.fragment.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.instruction.model.InstructionModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.entity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val repository: RecipeRepository) : ViewModel() {

    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()
    var instructionList: MutableLiveData<List<InstructionModel>> = MutableLiveData()



    fun fetchRecipeDetail(recipeId: Int) {

        val recipe = repository.getRecipe(recipeId)

        this.recipe.value = recipe
    }

     fun fetchMyRecipeDetail(recipeId: Int) {
         viewModelScope.launch {
             val myRecipeResult = repository.getMyRecipe(recipeId)
             recipe.value = myRecipeResult!!
         }

    }

    fun fetchRecipeInstruction(context: Context, recipeId: Int) {

        this.instructionList.value = repository.getRecipeInstructions(context, recipeId)
    }


}