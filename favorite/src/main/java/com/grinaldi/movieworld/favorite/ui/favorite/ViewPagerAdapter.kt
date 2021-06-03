package com.grinaldi.movieworld.favorite.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grinaldi.movieworld.core.utils.Constant

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = com.grinaldi.movieworld.favorite.ui.movies.FavoriteMovieFragment()
        val type = when (position) {
            0 -> Constant.TYPE_MOVIE
            1 -> Constant.TYPE_TV_SHOW
            else -> Constant.TYPE_MOVIE
        }
        fragment.arguments = Bundle().apply {
            putInt(
                com.grinaldi.movieworld.favorite.ui.movies.FavoriteMovieFragment.MOVIE_TYPE,
                type
            )
        }
        return fragment
    }
}