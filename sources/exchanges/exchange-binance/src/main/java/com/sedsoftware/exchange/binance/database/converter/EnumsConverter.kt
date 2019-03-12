package com.sedsoftware.exchange.binance.database.converter

import androidx.room.TypeConverter
import com.sedsoftware.exchange.binance.common.params.SymbolStatus
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.network.model.params.FilterType
import com.sedsoftware.exchange.binance.network.model.params.RateLimitInterval
import com.sedsoftware.exchange.binance.network.model.params.RateLimitType

class EnumsConverter {

    @TypeConverter
    fun fromBinanceCurrency(currency: BinanceCurrency): String = currency.name

    @TypeConverter
    fun toBinanceCurrency(text: String): BinanceCurrency = enumValueOf(text)

    @TypeConverter
    fun fromFilters(filter: FilterType): String = filter.name

    @TypeConverter
    fun toFilters(text: String): FilterType = enumValueOf(text)

    @TypeConverter
    fun fromRateLimitInterval(interval: RateLimitInterval): String = interval.name

    @TypeConverter
    fun toRateLimitInterval(text: String): RateLimitInterval = enumValueOf(text)

    @TypeConverter
    fun fromRateLimitType(type: RateLimitType): String = type.name

    @TypeConverter
    fun toRateLimitType(text: String): RateLimitType = enumValueOf(text)

    @TypeConverter
    fun fromSymbolStatus(status: SymbolStatus): String = status.name

    @TypeConverter
    fun toSymbolStatus(text: String): SymbolStatus = enumValueOf(text)
}
