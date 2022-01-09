package com.camo.template.database.remote.api

import javax.inject.Inject

class CGApiHelper @Inject constructor(private val cgService: CGService) : CGApiHelperIF {
    override suspend fun ping() = cgService.ping()
}