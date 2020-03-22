package com.sedsoftware.core.domain.repository

interface PairsInfoRepository {
    suspend fun downloadRemotePairsInfo()
    suspend fun markAsDownloaded()
}
