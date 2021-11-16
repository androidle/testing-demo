package com.leevinapp.testingdemo

import okhttp3.tls.HeldCertificate
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.InetAddress
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_cert() {
        val localhost = InetAddress.getByName("localhost").canonicalHostName
        val localhostCertificate = HeldCertificate.Builder()
            .addSubjectAlternativeName(localhost)
            .duration(10 * 365, TimeUnit.DAYS)
            .build()
        // Print public key
        println(localhostCertificate.certificatePem())
        // Print private key
        println(localhostCertificate.privateKeyPkcs8Pem())
    }
}
