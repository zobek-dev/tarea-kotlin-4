package com.example.alejandra_alvarez_20241205_herpm.models

import com.google.gson.annotations.SerializedName

data class Pharmacy(
    @SerializedName("local_id") val id: String,                          // ID único de la farmacia
    @SerializedName("local_nombre") val nombre: String,                 // Nombre de la farmacia
    @SerializedName("comuna_nombre") val comuna: String,                // Nombre de la comuna
    @SerializedName("localidad_nombre") val localidad: String,          // Nombre de la localidad
    @SerializedName("local_direccion") val direccion: String,           // Dirección de la farmacia
    @SerializedName("funcionamiento_hora_apertura") val apertura: String, // Hora de apertura
    @SerializedName("funcionamiento_hora_cierre") val cierre: String,   // Hora de cierre
    @SerializedName("local_telefono") val telefono: String?,            // Teléfono (puede ser nulo)
    @SerializedName("local_lat") val latitud: String?,                  // Latitud (puede ser nula)
    @SerializedName("local_lng") val longitud: String?,                 // Longitud (puede ser nula)
    @SerializedName("funcionamiento_dia") val dia: String               // Día de funcionamiento
)