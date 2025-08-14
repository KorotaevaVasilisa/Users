package ru.vsls.users.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("cell")
    val cell: String,
    @SerialName("dob")
    val dob: Dob,
    @SerialName("email")
    val email: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Id,
    @SerialName("location")
    val location: Location,
    @SerialName("name")
    val name: Name,
    @SerialName("nat")
    val nat: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("picture")
    val picture: Picture,
    @SerialName("registered")
    val registered: Registered
)