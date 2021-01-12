package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.tvshow.TvShowFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(tvShowFragment: TvShowFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}
