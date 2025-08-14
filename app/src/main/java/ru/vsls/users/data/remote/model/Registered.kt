package ru.vsls.users.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Registered(
    @SerialName("age")
    val age: Int,
    @SerialName("date")
    val date: String
)