package com.sedsoftware.core.domain.repository

interface PairInfoRepository {
    suspend fun downloadRemotePairsInfo()
    suspend fun markAsDownloaded()
}
