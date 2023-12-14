package com.tasty.recipesapp.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.App
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.fragment.adapter.RecipeAdapter
import com.tasty.recipesapp.fragment.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.repository.recipe.model.RecipeModel


class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private val viewModelFactory by lazy { App.instance.viewModelFactory }
    private val recipeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RecipeListViewModel::class.java)
    }

    companion object {
        const val TAG = "RecipesFragment"
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID_RECIPES = "selected_recipe_id_recipes"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            recipeViewModel.fetchRecipeData(it)
        }
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            for (recipe in recipes) {
                Log.d(TAG, "Recipe= ${recipe.name}")
                Log.d(TAG, "Recipe= ${recipe.description}")

            }

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateTo(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_recipes_to_recipes_detail, bundleOf(
                BUNDLE_EXTRA_SELECTED_RECIPE_ID_RECIPES to recipe.id
            )
        )
    }

    private fun showConfirmationDialog(context: Context, recipe: RecipeModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Recipe")
            .setMessage("Are you sure you want to delete this recipe?")
            .setPositiveButton("Yes") { _, _ ->
                // User clicked Yes, perform the deletion action
                recipeViewModel.deleteRecipe(recipe)
                Toast.makeText(context, "Recipe deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // User clicked Cancel, dismiss the dialog
                dialog.dismiss()
            }
            .show()
    }

    private fun onDeleteClick(recipe: RecipeModel) {
        showConfirmationDialog(requireContext(), recipe)
    }

}