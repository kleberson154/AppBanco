package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController

class SignUpActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup_activity)

        val linkLogin = findViewById<TextView>(R.id.textViewLinkLogin)
        val buttonSignUp = findViewById<TextView>(R.id.buttonSignUp)
        val editTextEmail = findViewById<TextView>(R.id.editTextEmailAddress)
        val editTextPassword = findViewById<TextView>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<TextView>(R.id.editTextConfirmPassword)

        val accountController = AccountController()

        buttonSignUp.setOnClickListener({
            accountController.createAccount(
                editTextEmail.text.toString(),
                editTextPassword.text.toString(),
                editTextConfirmPassword.text.toString(),
            )
        })

        linkLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}