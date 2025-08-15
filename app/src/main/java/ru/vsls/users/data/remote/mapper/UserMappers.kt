package ru.vsls.users.data.remote.mapper

import ru.vsls.users.data.remote.model.ApiResponse
import ru.vsls.users.data.remote.model.Dob
import ru.vsls.users.data.remote.model.Location
import ru.vsls.users.data.remote.model.Name
import ru.vsls.users.data.remote.model.Picture
import ru.vsls.users.data.remote.model.Registered
import ru.vsls.users.data.remote.model.Street
import ru.vsls.users.data.remote.model.User
import ru.vsls.users.domain.model.Dob as DomainDob
import ru.vsls.users.domain.model.Location as DomainLocation
import ru.vsls.users.domain.model.Name as DomainName
import ru.vsls.users.domain.model.Picture as DomainPicture
import ru.vsls.users.domain.model.Registered as DomainRegistered
import ru.vsls.users.domain.model.Street as DomainStreet
import ru.vsls.users.domain.model.User as DomainUser

fun Dob.toDomain(): DomainDob = DomainDob(
    age = age,
    date = date
)


fun Street.toDomain(): DomainStreet = DomainStreet(
    name = name,
    number = number
)

fun Location.toDomain(): DomainLocation = DomainLocation(
    city = city,
    country = country,
    state = state,
    street = street.toDomain()
)

fun Name.toDomain(): DomainName = DomainName(
    title = title,
    first = first,
    last = last
)

fun Picture.toDomain(): DomainPicture = DomainPicture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)

fun Registered.toDomain(): DomainRegistered = DomainRegistered(
    age = age,
    date = date
)

fun User.toDomain(): DomainUser = DomainUser(
    cell = cell,
    dob = dob.toDomain(),
    email = email,
    gender = gender,
    location = location.toDomain(),
    name = name.toDomain(),
    nat = nat,
    phone = phone,
    picture = picture.toDomain(),
    registered = registered.toDomain()
)

fun List<User>.toDomain(): List<DomainUser> = map { it.toDomain() }

fun ApiResponse.toDomain(): List<DomainUser> = results.map { it.toDomain() }
