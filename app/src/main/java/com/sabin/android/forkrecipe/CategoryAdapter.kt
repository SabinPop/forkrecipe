package com.sabin.android.forkrecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sabin.android.forkrecipe.R.id.*
import com.sabin.android.forkrecipe.R.layout.list_item_category
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context: Context, private val result: Categories) : BaseAdapter() {
    private var inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return result.categories.size
    }

    override fun getItem(position: Int): Any {
        return result.categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {

            view = inflater.inflate(list_item_category, parent, false)

            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(category_list_thumbnail) as ImageView
            holder.titleTextView = view.findViewById(category_list_title) as TextView
            holder.subtitleTextView = view.findViewById(category_list_subtitle) as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val thumbnailImageView = holder.thumbnailImageView


        val category = getItem(position) as Category

        titleTextView.text = category.strCategory
        subtitleTextView.text = category.strCategoryDescription

        Picasso.with(context).load(category.strCategoryThumb)
                .placeholder(android.R.mipmap.sym_def_app_icon).into(thumbnailImageView)

        return view
    }
    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}
