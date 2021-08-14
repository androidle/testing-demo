package com.leevinapp.testingdemo.utils

import java.nio.charset.Charset

class ResourceFile(
    private val filePath: String
) {
    private val loader: ClassLoader
        get() = Thread.currentThread().contextClassLoader ?: error("Class loader not available!")


    fun exist(): Boolean {
        return loader.getResource(filePath) != null
    }

    fun readText(charset: Charset = Charsets.UTF_8): String {
        if (!exist()) {
            error("File $filePath does not exist or cannot be accessed")
        }

        return loader
            .getResourceAsStream(filePath)
            .bufferedReader(charset)
            .use { it.readText() }
    }
}