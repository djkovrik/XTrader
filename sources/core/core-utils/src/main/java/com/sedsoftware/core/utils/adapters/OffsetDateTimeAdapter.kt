package com.sedsoftware.core.utils.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

class OffsetDateTimeAdapter {

    @FromJson
    fun offsetDateTimeFromJson(value: Long): OffsetDateTime =
        Instant.ofEpochMilli(value).atZone(ZoneOffset.UTC).toOffsetDateTime()

    @ToJson
    fun offsetDateTimeToJson(value: OffsetDateTime): String =
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value)
}
