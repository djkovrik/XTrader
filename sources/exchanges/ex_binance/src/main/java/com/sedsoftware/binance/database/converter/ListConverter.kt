package com.sedsoftware.binance.database.converter

import androidx.room.TypeConverter
import com.sedsoftware.binance.common.params.OrderType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*


class ListConverter {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<List<OrderType>> by lazy {
        val orderTypeList = Types.newParameterizedType(List::class.java, OrderType::class.java)
        return@lazy moshi.adapter<List<OrderType>>(orderTypeList)
    }

    @TypeConverter
    fun fromList(list: List<OrderType>): String =
        jsonAdapter.toJson(list)

    @TypeConverter
    fun toList(text: String): List<OrderType> =
        jsonAdapter.fromJson(text) ?: emptyList()
}
