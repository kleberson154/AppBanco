package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.exception.EmptyFieldException

class SignUpActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup_activity)

        val linkLogin = findViewById<TextView>(R.id.textViewLinkLogin)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val editTextEmail = findViewById<TextView>(R.id.editTextEmailSignUp)
        val editTextPassword = findViewById<TextView>(R.id.editTextPasswordSignUp)
        val editTextConfirmPassword = findViewById<TextView>(R.id.editTextConfirmPassword)
        val sharedPref = this.getSharedPreferences("save_profile", Context.MODE_PRIVATE)

        buttonSignUp.setOnClickListener{
            try {
                val emailSignUp = editTextEmail.text.toString()
                val passwordSignUp = editTextPassword.text.toString()
                val confirmPassword = editTextConfirmPassword.text.toString()

                if (emailSignUp.isBlank() || passwordSignUp.isBlank() || confirmPassword.isBlank()) {
                    throw EmptyFieldException("Todos os campos devem ser preenchidos.")
                }

                sharedPref.edit().apply {
                    putString("email", emailSignUp)
                    putString("password", passwordSignUp)
                    apply()
                }
                val intent = Intent(this, ProfileCreateActivity::class.java).putExtra("email", emailSignUp).putExtra("password", passwordSignUp)
                startActivity(intent)
            } catch (e: EmptyFieldException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        linkLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}