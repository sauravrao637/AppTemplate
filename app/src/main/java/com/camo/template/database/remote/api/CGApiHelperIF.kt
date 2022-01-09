package com.camo.template.database.remote.api

import retrofit2.Response

interface CGApiHelperIF {
    suspend fun ping(): Response<Any>
}
