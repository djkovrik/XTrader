package com.sedsoftware.binance.database.converter

import androidx.room.TypeConverter
import com.sedsoftware.binance.common.params.OrderType
import com.sedsoftware.binance.network.model.common.Filter
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

    private val jsonAdapterOrderType: JsonAdapter<List<OrderType>> by lazy {
        val orderTypeList = Types.newParameterizedType(List::class.java, OrderType::class.java)
        return@lazy moshi.adapter<List<OrderType>>(orderTypeList)
    }

    private val jsonAdapterFilters: JsonAdapter<List<Filter>> by lazy {
        val filtersList = Types.newParameterizedType(List::class.java, Filter::class.java)
        return@lazy moshi.adapter<List<Filter>>(filtersList)
    }

    @TypeConverter
    fun fromListOrderType(list: List<OrderType>): String =
        jsonAdapterOrderType.toJson(list)

    @TypeConverter
    fun toListOrderType(text: String): List<OrderType> =
        jsonAdapterOrderType.fromJson(text) ?: emptyList()

    @TypeConverter
    fun fromListFilters(list: List<Filter>): String =
        jsonAdapterFilters.toJson(list)

    @TypeConverter
    fun toListFilters(text: String): List<Filter> =
        jsonAdapterFilters.fromJson(text) ?: emptyList()
}
