package com.tasty.recipesapp.repository.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.fragment.viewmodel.ProfileViewModel
import com.tasty.recipesapp.fragment.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.fragment.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class ViewModelFactory(private val repository: RecipeRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RecipeListViewModel::class.java) -> {
                RecipeListViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecipeDetailViewModel::class.java) -> {
                RecipeDetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
