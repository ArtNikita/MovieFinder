package ru.nikitaartamonov.moviefinder.ui.main

class MainPresenter : MainContract.Presenter {
    private var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onMoviesMenuSelected() {
        view?.openMoviesScreen()
    }

    override fun onFavoritesMenuSelected() {
        view?.openFavoritesScreen()
    }

    override fun onSettingsMenuSelected() {
        view?.openSettingsScreen()
    }
}