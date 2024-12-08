package com.example.alejandra_alvarez_20241205_herpm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Tiempo de espera para mostrar el Splash Screen (3 segundos)
        Handler(Looper.getMainLooper()).postDelayed({
            // Verificamos si el usuario ya ingresó su nombre
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val userName = sharedPreferences.getString("USER_NAME", null)

            if (userName.isNullOrEmpty()) {
                // Si no hay nombre guardado, redirigimos a la pantalla de inicio de sesión
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                // Si ya existe un nombre guardado, redirigimos a la pantalla principal
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
            }
            finish() // Finalizamos la actividad Splash para no volver a ella
        }, 3000) // Duración del Splash Screen (en milisegundos)
    }
}
