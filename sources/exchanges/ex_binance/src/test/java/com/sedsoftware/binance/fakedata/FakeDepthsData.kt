package com.sedsoftware.binance.fakedata

import com.sedsoftware.binance.network.model.dto.PairDepthDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeDepthsData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<PairDepthDto> by lazy {
        val filtersList = Types.newParameterizedType(PairDepthDto::class.java, List::class.java, Any::class.java)
        return@lazy moshi.adapter<PairDepthDto>(filtersList)
    }

    fun getRawParsedData(jsonText: String): PairDepthDto =
        jsonAdapter.fromJson(jsonText) ?: PairDepthDto(0L, emptyList(), emptyList())

    fun getPredefinedParsedEntity(): PairDepthDto =
        PairDepthDto(
            lastUpdateId = 104388219L,
            bids = listOf(
                listOf(
                    "0.00860800",
                    "192.96000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00860100",
                    "14.57000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00848300",
                    "0.12000000",
                    emptyList<Any>()
                )
            ),
            asks = listOf(
                listOf(
                    "0.00860900",
                    "3.43000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00861000",
                    "897.42000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00875900",
                    "1.60000000",
                    emptyList<Any>()
                )
            )
        )
}
