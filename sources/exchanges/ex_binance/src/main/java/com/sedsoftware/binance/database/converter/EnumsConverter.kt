package com.sedsoftware.binance.database.converter

import androidx.room.TypeConverter
import com.sedsoftware.binance.enums.*

class EnumsConverter {

    @TypeConverter
    fun fromCandleInterval(interval: CandleInterval): String = interval.name

    @TypeConverter
    fun toCandleInterval(text: String): CandleInterval = enumValueOf(text)

    @TypeConverter
    fun fromCurrencyBinance(currency: CandleInterval): String = currency.name

    @TypeConverter
    fun toCurrencyBinance(text: String): CurrencyBinance = enumValueOf(text)

    @TypeConverter
    fun fromFilters(filter: Filters): String = filter.name

    @TypeConverter
    fun toFilters(text: String): Filters = enumValueOf(text)

    @TypeConverter
    fun fromOrderSide(side: OrderSide): String = side.name

    @TypeConverter
    fun toOrderSide(text: String): OrderSide = enumValueOf(text)

    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): String = status.name

    @TypeConverter
    fun toOrderStatus(text: String): OrderStatus = enumValueOf(text)

    @TypeConverter
    fun fromOrderType(type: OrderType): String = type.name

    @TypeConverter
    fun toOrderType(text: String): OrderType = enumValueOf(text)

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

    @TypeConverter
    fun fromTimeInForce(time: TimeInForce): String = time.name

    @TypeConverter
    fun toTimeInForce(text: String): TimeInForce = enumValueOf(text)
}
