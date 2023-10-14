package com.example.getmoview.domain.use_cases.local.local_movie_use_case

data class FavoritesMoviesUseCase(
    val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    val deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase,
    val addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase,
    val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase
)
