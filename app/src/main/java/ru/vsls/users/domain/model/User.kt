package ru.vsls.users.domain.model

data class User(
    val id: String,
    val gender: String,
    val title: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val pictureLarge: String,
    val pictureMedium: String,
    val pictureThumbnail: String,
    val dobDateIso: String,
    val dobAge: Int,
    val nat: String,
)
