package ru.vsls.users.presentation.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object UserDetails : Screen("details/{id}") {
        fun createRoute(id: String) = "details/$id"
    }
}