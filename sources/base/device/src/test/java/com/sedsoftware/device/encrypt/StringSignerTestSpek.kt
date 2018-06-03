package com.sedsoftware.device.encrypt

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class StringSignerTestSpek : Spek({

  given("String signer") {
    val signer = SignerImpl()

    on("signing with sha1") {
      val signedString = signer.signWithSha1("test", "thisismysupersecurekey")

      it("should return correct string") {
        signedString.should.equal("cc04a33744447f7897d0e24ba9898ab2ce807afe")
      }
    }

    on("signing with sha256") {
      val signedString = signer.signWithSha256("test", "thisismysupersecurekey")

      it("should return correct string") {
        signedString.should.equal("066a5aa77848f69f96b89ed1260e1cfa845d3c3b6c0e56c781472359761cd56a")
      }
    }
  }
})
