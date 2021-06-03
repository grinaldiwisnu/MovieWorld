package com.grinaldi.movieworld.core.utils

import com.grinaldi.movieworld.core.data.source.local.entities.DetailEntity
import com.grinaldi.movieworld.core.data.source.local.entities.MovieEntity
import com.grinaldi.movieworld.core.data.source.remote.response.DetailResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListMovieResponse
import com.grinaldi.movieworld.core.data.source.remote.response.ListTvResponse
import com.grinaldi.movieworld.core.domain.entities.Detail
import com.grinaldi.movieworld.core.domain.entities.Genre
import com.grinaldi.movieworld.core.domain.entities.Movie

object Mapper {
    fun movieEntityToDomain(list: List<MovieEntity>): List<Movie> {
        return list.map {
            Movie(
                it.id,
                it.overview,
                it.title,
                it.posterPath,
                it.voteAverage,
                it.movieType,
                it.isFavorite
            )
        }
    }

    fun movieResponseToEntity(responseList: ListMovieResponse): List<MovieEntity> {
        val movies = responseList.movieResponses
        return movies.map {
            MovieEntity(
                it.id,
                it.overview,
                it.title,
                it.posterPath,
                it.voteAverage,
                Constant.TYPE_MOVIE,
            )
        }
    }

    fun tvShowResponsesToEntity(responseList: ListTvResponse): List<MovieEntity> {
        val movies = responseList.tvShowResponses
        return movies.map {
            MovieEntity(
                it.id,
                it.overview,
                it.name,
                it.posterPath,
                it.voteAverage,
                Constant.TYPE_TV_SHOW,
            )
        }
    }

    fun detailEntityToDomain(entity: DetailEntity): Detail {
        val genres = entity.genres.map { Genre(it) }
        return Detail(
            entity.overview,
            entity.title,
            entity.posterPath,
            entity.voteAverage,
            entity.tagLine,
            genres,
            entity.id,
            entity.homepage,
            entity.status,
            entity.popularity,

            )
    }

    fun detailResponseToEntity(response: DetailResponse, movieType: Int): DetailEntity {
        val genres = response.genreResponses.map { it.name }
        return DetailEntity(
            response.id,
            response.overview,
            response.title,
            response.posterPath,
            response.voteAverage,
            response.popularity,
            response.tagLine,
            genres,
            response.homepage,
            response.status,
            movieType
        )
    }
}