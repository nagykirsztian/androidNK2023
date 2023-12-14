package com.tasty.recipesapp.fragment

import InstructionAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.App
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.fragment.viewmodel.RecipeDetailViewModel


class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { App.instance.viewModelFactory }
    private val recipeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RecipeDetailViewModel::class.java)
    }

    private var player: ExoPlayer? = null

    companion object {
        val TAG = "RecipeDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRecipeId = arguments?.getInt(ProfileFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID_PROFILE)
        val recipeId = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID_RECIPES)
        Log.d(TAG,"$recipeId, $myRecipeId")
        if (myRecipeId != null && myRecipeId != 0){
            context?.let {
                recipeViewModel.fetchMyRecipeDetail(myRecipeId)
                context?.let {
                    recipeViewModel.fetchRecipeInstruction(it, myRecipeId)

                }
                Log.d(TAG, "Show details for recipe with ID= $myRecipeId")
            }
        }
        else if (recipeId != null && recipeId != 0){
            context?.let {
                    recipeViewModel.fetchRecipeDetail(recipeId)
                    context?.let {
                        recipeViewModel.fetchRecipeInstruction(it, recipeId)

                }
                Log.d(TAG, "Show details for recipe with ID= $recipeId")
            }
        }




        recipeViewModel.recipe.observe(viewLifecycleOwner) { recipeDetail ->
            if (recipeDetail != null) {
                binding.recipeTitle.text = recipeDetail.name
                binding.recipeDescription.text = recipeDetail.description
                Glide.with(requireContext())
                    .load(recipeDetail.thumbnailUrl)
                    .into(binding.recipePicture)

            } else {
                Log.e(TAG, "RecipeDetail is null")
            }
        }
        recipeViewModel.instructionList.observe(viewLifecycleOwner) { instructions ->
            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewInstructions)
            val adapter = InstructionAdapter(instructions)
            recyclerView.adapter = adapter
//            for (instruction in instructions) {
//                Log.d(TAG, "Instruction= ${instruction}")
//
//            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()

    }
}