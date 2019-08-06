package com.sabin.android.forkrecipe

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabin.android.forkrecipe.R.layout.fragment_favorites

class FavoritesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_favorites, container, false)
    }

    companion object {
        fun newInstance() : FavoritesFragment = FavoritesFragment()
    }

}
