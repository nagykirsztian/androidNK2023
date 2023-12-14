package com.tasty.recipesapp.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeAdapter(private var recipesList: List<RecipeModel>, private val context: Context) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeName: TextView
        val recipeDescription: TextView
        val recipePicture: ImageView

        init {
            // Define click listener for the ViewHolder's View
            recipeName = view.findViewById(R.id.recipeName)
            recipeDescription = view.findViewById(R.id.recipeDescription)
            recipePicture = view.findViewById(R.id.recipePicture)


        }
    }

    // Interface to handle item clicks
    interface OnItemClickListener {
        fun onItemClick(recipe: RecipeModel)
        fun onItemLongClick(recipe: RecipeModel)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // Setter method for the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentRecipe = recipesList[position]
        viewHolder.recipeName.text = currentRecipe.name
        viewHolder.recipeDescription.text = currentRecipe.description
        viewHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(currentRecipe)
        }

        viewHolder.itemView.setOnLongClickListener {
            onItemClickListener?.onItemLongClick(currentRecipe)
            true
        }

        Glide.with(context)
            .load(currentRecipe.thumbnailUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(viewHolder.recipePicture)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipesList.size
}