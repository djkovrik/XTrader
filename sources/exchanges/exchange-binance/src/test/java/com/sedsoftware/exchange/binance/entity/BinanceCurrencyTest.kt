package com.sedsoftware.exchange.binance.entity

import com.sedsoftware.exchange.binance.fakedata.FakeSymbolsInfoData
import com.sedsoftware.exchange.binance.network.model.dto.PairsInfoDto
import com.sedsoftware.exchange.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BinanceCurrencyTest : Spek({

    describe("Full symbols list parsing") {

        val fakeDataSource = FakeSymbolsInfoData()
        val jsonText: String = readStringFromResource("/symbols_info_full.json")
        val parsedDto: PairsInfoDto = fakeDataSource.getRawParsedDto(jsonText)

        context("Parse existing currency pairs") {

            parsedDto.symbols.forEach { symbol ->

                it("Base symbol ${symbol.baseAsset} should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol.baseAsset).should.not.equal(null)
                }

                it("Quote symbol ${symbol.quoteAsset} should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol.quoteAsset).should.not.equal(null)
                }
            }
        }

        context("Check if defined pair still active") {

            val baseSymbols: Set<String> = parsedDto.symbols.map { it.baseAsset }.toSet()
            val quoteSymbols: Set<String> = parsedDto.symbols.map { it.quoteAsset }.toSet()

            BinanceCurrency.values().forEach { symbol ->
                it("Enum value ${symbol.name} should be active") {
                    true.should.satisfy {
                        baseSymbols.contains(symbol.name) || quoteSymbols.contains(symbol.name)
                    }
                }
            }
        }
    }
})
