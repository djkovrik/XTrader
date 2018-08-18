package com.sedsoftware.device

import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CalculatorTest : Spek({

    describe("A calculator") {
        val calculator by memoized { Calculator() }

        it("should return 4") {
            calculator.add(2, 2).should.equal(4)
        }
    }
})

class Calculator {
    fun add(x: Int, y: Int) = x + y
}