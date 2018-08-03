package com.sedsoftware.device.encrypt

import com.sedsoftware.core.device.Signer
import com.winterbe.expekt.should
import org.junit.Test

class StringSignerTest {

    private val signer: Signer = SignerImpl()

    companion object {
        private const val SIGNATURE_KEY = "thisismysupersecurekey"
    }

    @Test
    fun test_makeSignSha1() {

        // Arrange
        val stringsMap = mapOf(
            "test" to "cc04a33744447f7897d0e24ba9898ab2ce807afe",
            "limit=10" to "c6b3514b77dd9d045e5fe189070666ca0783c45d",
            "HelloWorld" to "b6a489d7abed9c29897355cc420633b43bdb8938"
        )

        // Assert
        stringsMap.forEach { (text, signedTest) ->
            signedTest.should.equal(signer.signWithSha1(text, SIGNATURE_KEY))
        }
    }

    @Test
    fun test_makeSignSha256() {

        // Arrange
        val stringsMap = mapOf(
            "test" to "066a5aa77848f69f96b89ed1260e1cfa845d3c3b6c0e56c781472359761cd56a",
            "limit=10" to "844bd7866deca6e0ebdb4e422c6e1b717e6b4c4ab283b5cb7969705cc31a8477",
            "HelloWorld" to "9bbd2570d947c309f4386159ff8aad0296de815a3ba14763a4d93890d1be7214"
        )

        // Assert
        stringsMap.forEach { (text, signedTest) ->
            signedTest.should.equal(signer.signWithSha256(text, SIGNATURE_KEY))
        }
    }
}
