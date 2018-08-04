package com.sedsoftware.device.encrypt

import com.sedsoftware.core.device.Signer
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class ParamsSigner : Signer {

    companion object {
        private const val SHA1_ALGORITHM = "HmacSHA1"
        private const val SHA256_ALGORITHM = "HmacSHA256"
    }

    override fun signWithSha1(text: String, key: String): String =
        text.signWith(key, SHA1_ALGORITHM)

    override fun signWithSha256(text: String, key: String): String =
        text.signWith(key, SHA256_ALGORITHM)

    private fun String.signWith(key: String, algorithm: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), algorithm)
        val mac = Mac.getInstance(algorithm)
        mac.init(keySpec)

        return bytesToHex(mac.doFinal(this.toByteArray(Charsets.UTF_8)))
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = "0123456789abcdef"
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach { byte ->
            val i = byte.toInt()
            result.append(hexChars[i shr 4 and 0x0f])
            result.append(hexChars[i and 0x0f])
        }

        return result.toString()
    }
}
