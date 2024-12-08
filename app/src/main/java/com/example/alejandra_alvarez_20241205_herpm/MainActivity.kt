package com.example.alejandra_alvarez_20241205_herpm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alejandra_alvarez_20241205_herpm.adapters.PharmacyAdapter
import com.example.alejandra_alvarez_20241205_herpm.models.Pharmacy
import com.example.alejandra_alvarez_20241205_herpm.utils.ApiClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PharmacyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a elementos de la interfaz
        val textViewWelcome: TextView = findViewById(R.id.textViewWelcome)
        val buttonLogout: Button = findViewById(R.id.buttonLogout)
        recyclerView = findViewById(R.id.recyclerView)

        // Configuración del RecyclerView
        adapter = PharmacyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Recuperar el nombre del usuario
        val userName = intent.getStringExtra("USER_NAME") ?: "Usuario"
        textViewWelcome.text = "Bienvenido: $userName"

        // Obtener y mostrar farmacias
        fetchPharmacies()

        // Configuración del botón de cerrar sesión
        buttonLogout.setOnClickListener {
            // Limpiar preferencias de usuario
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()

            // Redirigir al LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun fetchPharmacies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jsonArray = ApiClient.fetchPharmacies()
                if (jsonArray != null) {
                    val type = object : TypeToken<List<Pharmacy>>() {}.type
                    val pharmacies: List<Pharmacy> = Gson().fromJson(jsonArray, type)

                    // Actualizar la lista en el hilo principal
                    runOnUiThread {
                        adapter.submitList(pharmacies)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "No se encontraron farmacias",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al obtener los datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
