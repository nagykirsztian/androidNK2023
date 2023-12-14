package com.tasty.recipesapp.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.App
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.fragment.adapter.RecipeAdapter
import com.tasty.recipesapp.fragment.viewmodel.ProfileViewModel
import com.tasty.recipesapp.repository.recipe.model.RecipeModel


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { App.instance.viewModelFactory }
    private val recipeViewModel: ProfileViewModel  by viewModels  {
       viewModelFactory
    }

    companion object {
        val TAG = "ProfileFragment"
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID_PROFILE = "selected_recipe_id_profile"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()

        binding.fabAddRecipe.setOnClickListener {
            navController.navigate(R.id.action_profile_to_newRecipe)
        }
        context?.let {
//            recipeViewModel.fetchRecipeData(it)
            recipeViewModel.getAllRecipes()
        }
        recipeViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            val recipeAdapter = context?.let { RecipeAdapter(recipes, it) }
            recipeAdapter?.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
                override fun onItemClick(recipe: RecipeModel) {
                    navigateTo(recipe)
                }
                override fun onItemLongClick(recipe: RecipeModel) {
                    onDeleteClick(recipe)
                }
            })

            val recyclerView: RecyclerView = binding.recyclerView
            recyclerView.adapter = recipeAdapter
        }
//        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
//            val randomRecipes = recipes.shuffled().take(3)
//            val recipeAdapter = context?.let { RecipeAdapter(randomRecipes, it) }
//            recipeAdapter?.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
//                override fun onItemClick(recipe: RecipeModel) {
//                    navigateTo(recipe)
//                }
//            })
//            val recyclerView: RecyclerView = binding.recyclerView
//            recyclerView.adapter = recipeAdapter
//        }
    }

    private fun navigateTo(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_profile_to_recipes_detail, bundleOf(
                BUNDLE_EXTRA_SELECTED_RECIPE_ID_PROFILE to recipe.id
            )
        )
    }

    private fun showConfirmationDialog(context: Context, recipe: RecipeModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Recipe")
            .setMessage("Are you sure you want to delete this recipe?")
            .setPositiveButton("Yes") { _, _ ->
                recipeViewModel.removeRecipe(recipe)
                Toast.makeText(context, "Recipe deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun onDeleteClick(recipe: RecipeModel) {
        showConfirmationDialog(requireContext(), recipe)
    }

}