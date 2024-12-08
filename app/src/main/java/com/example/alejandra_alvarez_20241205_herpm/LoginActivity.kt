package com.example.alejandra_alvarez_20241205_herpm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos de la interfaz
        val editTextName: EditText = findViewById(R.id.editTextName)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)

        // Configuramos el botón de envío
        buttonSubmit.setOnClickListener {
            val userName = editTextName.text.toString().trim()

            if (userName.isNotEmpty()) {
                // Guardamos el nombre del usuario en SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                sharedPreferences.edit().putString("USER_NAME", userName).apply()

                // Redirigimos a MainActivity y pasamos el nombre
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
                finish() // Finalizamos LoginActivity para que no se pueda volver
            } else {
                // Mostramos un mensaje si el campo está vacío
                Toast.makeText(this, "Por favor, ingrese su nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
