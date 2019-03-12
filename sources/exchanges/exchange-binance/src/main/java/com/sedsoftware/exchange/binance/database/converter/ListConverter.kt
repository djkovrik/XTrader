package com.sedsoftware.exchange.binance.database.converter

import androidx.room.TypeConverter
import com.sedsoftware.exchange.binance.common.params.OrderType
import com.sedsoftware.exchange.binance.network.model.params.Filter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class ListConverter {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapterFilters: JsonAdapter<List<Filter>> by lazy {
        val filtersList = Types.newParameterizedType(List::class.java, Filter::class.java)
        return@lazy moshi.adapter<List<Filter>>(filtersList)
    }

    private val jsonAdapterOrderType: JsonAdapter<List<OrderType>> by lazy {
        val orderTypeList = Types.newParameterizedType(List::class.java, OrderType::class.java)
        return@lazy moshi.adapter<List<OrderType>>(orderTypeList)
    }

    @TypeConverter
    fun fromListFilters(list: List<Filter>): String =
        jsonAdapterFilters.toJson(list)

    @TypeConverter
    fun toListFilters(text: String): List<Filter> =
        jsonAdapterFilters.fromJson(text) ?: emptyList()

    @TypeConverter
    fun fromListOrderType(list: List<OrderType>): String =
        jsonAdapterOrderType.toJson(list)

    @TypeConverter
    fun toListOrderType(text: String): List<OrderType> =
        jsonAdapterOrderType.fromJson(text) ?: emptyList()
}
