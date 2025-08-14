package ru.vsls.users.domain.model

data class Location(
    val city: String,
    val country: String,
    val state: String,
    val street: Street,
)
