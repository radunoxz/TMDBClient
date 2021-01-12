package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.presentation.artist.ArtistFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(artistFragment: ArtistFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}
