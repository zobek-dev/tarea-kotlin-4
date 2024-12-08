package com.example.alejandra_alvarez_20241205_herpm.utils

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object ApiClient {
    private const val BASE_URL = "https://midas.minsal.cl/farmacia_v2/WS/getLocalesTurnos.php"

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    /**
     * Obtiene las farmacias de turno como un JsonArray.
     * @return JsonArray con los datos de las farmacias.
     * @throws IOException si ocurre algún error.
     */
    @Throws(IOException::class)
    fun fetchPharmacies(): JsonArray? {
        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val responseString = response.body?.string()
            return if (!responseString.isNullOrEmpty()) {
                // Convertimos el string a JsonArray
                JsonParser.parseString(responseString).asJsonArray
            } else {
                null
            }
        } else {
            throw IOException("Error en la petición: ${response.code}")
        }
    }
}
