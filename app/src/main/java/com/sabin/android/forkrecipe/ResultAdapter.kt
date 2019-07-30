package com.sabin.android.forkrecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sabin.android.forkrecipe.R.id.*
import com.sabin.android.forkrecipe.R.layout.*
import com.squareup.picasso.Picasso

class ResultAdapter(private val context: Context, private val result: Result) : BaseAdapter() {
    private var inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return result.meals.size
    }

    override fun getItem(position: Int): Any {
        return result.meals[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(
                list_item_recipe, parent, false)

        val titleTextView = rowView.findViewById(
                recipe_list_title
        ) as TextView

        val subtitleTextView = rowView.findViewById(
                recipe_list_subtitle
        ) as TextView

        val detailTextView = rowView.findViewById(
                recipe_list_detail
        ) as TextView

        val thumbnailImageView = rowView.findViewById(
                recipe_list_thumbnail
        ) as ImageView

        val meal = getItem(position) as Meal

        titleTextView.text = meal.strMeal
        subtitleTextView.text = meal.idMeal
        detailTextView.text = meal.strInstructions

        Picasso.with(context).load(meal.strMealThumb)
                .placeholder(android.R.mipmap.sym_def_app_icon).into(thumbnailImageView)

        return rowView
    }
}
