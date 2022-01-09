package com.camo.template.database.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface CGService {
    @GET("ping")
    suspend fun ping(): Response<Any>
}