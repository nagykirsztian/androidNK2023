package com.tasty.recipesapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.App
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.fragment.viewmodel.ProfileViewModel
import com.tasty.recipesapp.fragment.viewmodel.Result
import com.tasty.recipesapp.repository.recipe.entity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeDTO
import kotlinx.coroutines.launch


class NewRecipeFragment : Fragment() {
    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { App.instance.viewModelFactory }
    private val recipeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    companion object {
        const val TAG = "NewRecipeFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        binding.btnSave.setOnClickListener {
            val recipeTitle = binding.recipeTitle.text.toString()
            val recipeDescription = binding.recipeDescription.text.toString()
            val recipePictureURL = binding.recipePictureURL.text.toString()

            lifecycleScope.launch {
                val id = App.instance.repository.getMaxId()
            }

            Log.d("FATAL", id.toString())
            val recipeData = RecipeDTO(
                name = recipeTitle,
                description = recipeDescription,
                thumbnail_url = recipePictureURL,
                instructions = emptyList(),
                id = id
            )
            val json = Gson().toJson(recipeData)

            val newRecipe = RecipeEntity(
                name = recipeTitle,
                description = recipeDescription,
                pictureUrl = recipePictureURL,
                json = json
            )

            recipeViewModel.insertRecipe(newRecipe).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Success -> {
                        findNavController().navigateUp()
                    }

                    is Result.Error -> {
                        Toast.makeText(
                            context,
                            "Failed to save recipe: ${result.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {}
                }
            }
        }
        return binding.root
    }

}