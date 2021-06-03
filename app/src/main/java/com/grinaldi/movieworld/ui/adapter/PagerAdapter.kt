package com.grinaldi.movieworld.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grinaldi.movieworld.core.utils.Constant
import com.grinaldi.movieworld.ui.movie.MovieFragment

class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieFragment()
        val type = when (position) {
            0 -> Constant.TYPE_MOVIE
            1 -> Constant.TYPE_TV_SHOW
            else -> Constant.TYPE_MOVIE
        }
        fragment.arguments = Bundle().apply {
            putInt(MovieFragment.MOVIE_TYPE, type)
        }
        return fragment
    }
}