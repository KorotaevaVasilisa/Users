package ru.vsls.users.data.local.mapper

import ru.vsls.users.data.local.entity.UserEntity
import ru.vsls.users.domain.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        gender = gender,
        title = title,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        streetNumber = streetNumber,
        streetName = streetName,
        city = city,
        state = state,
        country = country,
        pictureLarge = pictureLarge,
        pictureMedium = pictureMedium,
        pictureThumbnail = pictureThumbnail,
        dobDateIso = dobDateIso,
        dobAge = dobAge,
        nat = nat,
    )
}

fun UserEntity.toDomain(): User{
    return User(
        id = id,
        gender = gender,
        title = title,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        streetNumber = streetNumber,
        streetName = streetName,
        city = city,
        state = state,
        country = country,
        pictureLarge = pictureLarge,
        pictureMedium = pictureMedium,
        pictureThumbnail = pictureThumbnail,
        dobDateIso = dobDateIso,
        dobAge = dobAge,
        nat = nat,
    )
}