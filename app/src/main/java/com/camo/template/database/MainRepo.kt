package com.camo.template.database.repos

import com.camo.template.database.local.LocalAppDb
import com.camo.template.database.local.model.Coin
import com.camo.template.database.remote.api.CGApiHelper
import com.camo.template.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(private val db: LocalAppDb, val cgApiHelper: CGApiHelper) {
    suspend fun pingCG(): Flow<Resource<Response<Any>>> {
        return flow {
            emit(Resource.loading(data = null))
            try {
                val res = cgApiHelper.ping()
                Timber.d(res.toString())
                if (res.isSuccessful && res.code() == 200) emit(Resource.success(res))
                else {
                    Timber.d(res.toString())
                    emit(Resource.error(res, "Couldn't ping server"))
                }
            } catch (e: Exception) {
                Timber.d(e)
                emit(Resource.error(null, "Couldn't ping server"))
            }
        }
    }

    suspend fun addCoins(coins: ArrayList<Coin>) {
        db.coinDao().addCoins(coins)
    }
}