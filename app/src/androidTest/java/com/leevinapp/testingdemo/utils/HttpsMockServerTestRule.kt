package com.leevinapp.testingdemo.utils

import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import org.junit.rules.ExternalResource

class HttpsMockServerTestRule : ExternalResource() {

    val server = MockWebServer()

    override fun before() {
        val localhostCertificate =
            HeldCertificate.decode("instrumentation_cert.pem".loadString())

        val serverCertificates = HandshakeCertificates.Builder()
            .heldCertificate(localhostCertificate)
            .build()

        server.useHttps(
            serverCertificates.sslSocketFactory(),
            tunnelProxy = false,
        )

        server.start(8000)
    }

    override fun after() {
        server.shutdown()
    }
}

fun String.loadString(): String =
    object {}.javaClass.getResource("/$this").readText()