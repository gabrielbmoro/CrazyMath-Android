package com.gabrielbmoro.crazymath.repository.assets

import android.content.res.AssetManager
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

object JsonAssetsReader {

    fun read(assetManager: AssetManager, fileName: String): String? {
        var inputStream: InputStream? = null
        var jsonString: String?

        try {
            inputStream = assetManager.open(fileName)
            val byteArray = inputStream.readBytes()
            jsonString = String(byteArray)
        } catch (ioException: IOException) {
            Timber.e(ioException)
            jsonString = null
        } finally {
            inputStream?.close()
        }

        return jsonString
    }

}