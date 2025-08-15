package ru.vsls.users.data.local.mapper

import ru.vsls.users.data.local.entity.UserEntity
import ru.vsls.users.domain.model.User
import java.util.UUID

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = 0,
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
}