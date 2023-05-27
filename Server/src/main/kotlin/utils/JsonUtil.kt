package utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Утилита для работы с файлами JSON.
 */
object JsonUtil {
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private fun <T> fromJson(jsonString: String, serializer: KSerializer<T>): T? {
        return try {
            json.decodeFromString(serializer, jsonString)
        } catch (e: Exception) {
            null
        }
    }


    fun <T> loadFromFile(fileName: String, serializer: KSerializer<T>): T? {
        return try {
            val jsonString = File(fileName).readText()
            fromJson(jsonString, serializer)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun <T> saveToFile(obj: T, fileName: String, serializer: KSerializer<T>) {
        try {
            val jsonString = json.encodeToString(serializer, obj)
            File(fileName).writeText(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
