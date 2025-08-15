package ru.vsls.users.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey val id: String,
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
){
    companion object{
        const val TABLE_NAME = "users"
    }
}