package ru.vsls.users.domain.model

data class User(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val location: Location,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered,
)
