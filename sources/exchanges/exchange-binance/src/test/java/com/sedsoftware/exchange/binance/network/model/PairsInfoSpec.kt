package com.sedsoftware.exchange.binance.network.model

import com.sedsoftware.core.test.blockingMemoized
import com.sedsoftware.core.test.get
import com.sedsoftware.core.utils.adapter.OffsetDateTimeAdapter
import com.sedsoftware.exchange.binance.Urls
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

class PairsInfoSpec : Spek({

    val adapter = OffsetDateTimeAdapter()
    val now: OffsetDateTime = Instant.now().atZone(ZoneOffset.UTC).toOffsetDateTime()
    val response: PairsInfo? by blockingMemoized { get(Urls.GET_CURRENCY_PAIRS, adapter, PairsInfo::class.java) }

    describe("Binance exchangeInfo response test") {

        context("Check response state") {
            it("Response should not be null") {
                response.should.not.be.`null`
            }
        }

        context("Check response fields parsing") {
            it("timezone should not be empty") {
                response?.timezone.should.not.be.empty
            }
            it("rateLimits should not be empty") {
                response?.rateLimits.should.not.be.empty
            }
            it("symbols should not be empty") {
                response?.symbols.should.not.be.empty
            }
        }

        context("Check response date parsing") {
            it("serverTime should not be null") {
                response?.serverTime.should.not.be.`null`
            }
            it("serverTime should have correct year") {
                response?.serverTime?.year.should.be.equal(now.year)
            }
            it("serverTime should have correct month") {
                response?.serverTime?.month.should.be.equal(now.month)
            }
            it("serverTime should have correct day") {
                response?.serverTime?.dayOfMonth.should.be.equal(now.dayOfMonth)
            }
            it("serverTime should have correct hour") {
                response?.serverTime?.hour.should.be.equal(now.hour)
            }
        }
    }
})