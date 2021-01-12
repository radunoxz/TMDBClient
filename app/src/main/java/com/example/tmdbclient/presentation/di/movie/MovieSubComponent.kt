package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.presentation.movie.MovieFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(movieFragment: MovieFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}
