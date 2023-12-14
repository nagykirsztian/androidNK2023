package com.tasty.recipesapp

import android.app.Application
import com.tasty.recipesapp.repository.factory.ViewModelFactory
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.entity.RecipeDatabase

class App : Application() {

    private val database by lazy { RecipeDatabase.getDatabase(this) }
    val repository by lazy { RecipeRepository(database.recipeDao()) }
    val viewModelFactory by lazy { ViewModelFactory(repository) }

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}