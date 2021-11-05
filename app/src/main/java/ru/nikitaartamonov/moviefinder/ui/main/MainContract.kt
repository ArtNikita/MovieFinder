package ru.nikitaartamonov.moviefinder.ui.main

class MainContract {

    interface View {
        fun openMoviesScreen()
        fun openFavoritesScreen()
        fun openSettingsScreen()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onMoviesMenuSelected()
        fun onFavoritesMenuSelected()
        fun onSettingsMenuSelected()
    }
}