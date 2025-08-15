package ru.vsls.users.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("city")
    val city: String,
    @SerialName("country")
    val country: String,
    @SerialName("state")
    val state: String,
    @SerialName("street")
    val street: Street
)