package com.gabrielbmoro.crazymath.repository.assets

import android.content.res.Resources
import com.gabrielbmoro.crazymath.domain.model.CrossNumberConfig
import com.gabrielbmoro.crazymath.domain.model.PreviewConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetsDataSourceRepositoryImpl(
        private val resources: Resources,
        private val gson: Gson
) {

    fun getPreviewConfiguration(): PreviewConfig {
        val json = getJsonFromAsset(CROSS_NUMBER_PREVIEW_FILE_NAME)
        return convertToPreview(json)
    }

    fun getCrossNumberConfigurations(): List<CrossNumberConfig> {
        val json = getJsonFromAsset(CROSS_NUMBER_CONFIGURATION_FILE_NAME)
        return convertToCrossNumberConfig(json)
    }

    @Suppress("SameParameterValue")
    private fun getJsonFromAsset(key: String): String {
        return JsonAssetsReader.read(resources.assets, key) ?: ""
    }

    private fun convertToCrossNumberConfig(json: String): List<CrossNumberConfig> {
        val type = object : TypeToken<List<CrossNumberConfig>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun convertToPreview(json: String): PreviewConfig {
        return gson.fromJson(json, PreviewConfig::class.java)
    }

    companion object {
        private const val CROSS_NUMBER_PREVIEW_FILE_NAME = "preview_cross_number_config.json"
        private const val CROSS_NUMBER_CONFIGURATION_FILE_NAME = "cross_number_config.json"
    }
}