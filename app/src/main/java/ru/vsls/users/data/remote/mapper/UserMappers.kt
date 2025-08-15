package ru.vsls.users.data.remote.mapper

import ru.vsls.users.data.remote.model.ApiResponse
import ru.vsls.users.data.remote.model.User
import ru.vsls.users.domain.model.User as DomainUser
import java.util.UUID

private fun buildStableId(email: String, phone: String): String =
    UUID.nameUUIDFromBytes("${email.lowercase()}|${phone}".toByteArray()).toString()

fun User.toDomain(): DomainUser = DomainUser(
    id = buildStableId(email = email, phone = phone),
    gender = gender,
    title = name.title,
    firstName = name.first,
    lastName = name.last,
    email = email,
    phone = phone,
    streetNumber = location.street.number,
    streetName = location.street.name,
    city = location.city,
    state = location.state,
    country = location.country,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail,
    dobDateIso = dob.date,
    dobAge = dob.age,
    nat = nat,
)

fun List<User>.toDomain(): List<DomainUser> = map { it.toDomain() }

fun ApiResponse.toDomain(): List<DomainUser> = results.map { it.toDomain() }
