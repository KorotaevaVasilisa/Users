package ru.vsls.users.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.vsls.users.data.remote.api.UsersApiService

private const val BASE_URL = "https://randomuser.me/"

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    val contentType = "application/json".toMediaType()
    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType).apply { })
        .baseUrl(BASE_URL)
        .build()
}

fun provideService(retrofit: Retrofit): UsersApiService =
    retrofit.create(UsersApiService::class.java)

val networkModule = module {
    single { provideRetrofit() }
    single<UsersApiService> { provideService(get()) }
}