package ru.vsls.users.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.vsls.users.data.remote.model.ApiResponse

interface UsersApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int = 10,
    ): ApiResponse
}