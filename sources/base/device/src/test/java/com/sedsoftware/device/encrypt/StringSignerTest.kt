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
      "admin" to "f9d0ddab8b8bb1dc9b682481523fa583d37c829c",
      "limit=10" to "c6b3514b77dd9d045e5fe189070666ca0783c45d",
      "API_KEY=xxxxxxxxx" to "f4c70e2c87c49e1cf065aca8ab36ba56bae541f4",
      "HelloWorld" to "b6a489d7abed9c29897355cc420633b43bdb8938",
      "1234567890" to "84aacc4826e7032317afad90d8be15e3805b5e6b"
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
      "admin" to "a3a904539f1a7213d7e30c2be179c318b4f23e2f273b3f59258e8d76edd140c1",
      "limit=10" to "844bd7866deca6e0ebdb4e422c6e1b717e6b4c4ab283b5cb7969705cc31a8477",
      "API_KEY=xxxxxxxxx" to "49a53a1dc6fa622504a02b458a4e4962f5e5b9df388eeb97bfece929d2029f76",
      "HelloWorld" to "9bbd2570d947c309f4386159ff8aad0296de815a3ba14763a4d93890d1be7214",
      "1234567890" to "710e250e863b7438bc6c84f929aa14f91bec33a6ae787d77386f69a2fa75be35"
    )

    // Assert
    stringsMap.forEach { (text, signedTest) ->
      signedTest.should.equal(signer.signWithSha256(text, SIGNATURE_KEY))
    }
  }
}
