package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.exception.EmptyFieldException

class LoginActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)

        val emailLogin = findViewById<EditText>(R.id.editTextEmailLogin)
        val passwordLogin = findViewById<EditText>(R.id.editTextPasswordLogin)
        val linkSignUp = findViewById<TextView>(R.id.textViewLinkSignUp)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener{
            try{
                val email = emailLogin.text.toString()
                val password = passwordLogin.text.toString()

                if (email.isBlank() || password.isBlank()) {
                    throw EmptyFieldException("Todos os campos devem ser preenchidos.")
                }
            }catch (e: EmptyFieldException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        linkSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}
