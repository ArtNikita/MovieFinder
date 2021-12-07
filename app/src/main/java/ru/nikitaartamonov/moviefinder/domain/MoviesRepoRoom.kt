package ru.nikitaartamonov.moviefinder.domain

interface MoviesRepoRoom {
    fun getMoviesList(callback: ((List<PreviewMovieEntity>) -> Unit))
    fun getSize(callback: (Int) -> Unit)
    fun addMovie(movieEntity: PreviewMovieEntity)
    fun deleteMovie(id: Long, callback: (Boolean) -> Unit)
    fun getMovie(id: Long, callback: (PreviewMovieEntity?) -> Unit)
}