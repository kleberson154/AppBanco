package com.kleberson.appbanco.view

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.exception.EmptyFieldException
import com.kleberson.appbanco.exception.FailedLoginException

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)

        val emailLogin = findViewById<EditText>(R.id.editTextEmailLogin)
        val passwordLogin = findViewById<EditText>(R.id.editTextPasswordLogin)
        val linkSignUp = findViewById<TextView>(R.id.textViewLinkSignUp)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val accountController = AccountController(this)

        getSharedPreferences("save_profile", MODE_PRIVATE).getString("email", "")?.let {
            emailLogin.setText(it)
        }

        getSharedPreferences("save_profile", MODE_PRIVATE).getString("password", "")?.let {
            passwordLogin.setText(it)
        }

        buttonLogin.setOnClickListener{
            try{
                val email = emailLogin.text.toString()
                val password = passwordLogin.text.toString()

                if (email.isBlank() || password.isBlank()) {
                    throw EmptyFieldException()
                }

                val profile = accountController.login(email, password)
                if(profile != null) {
                    val sharedPreferences = getSharedPreferences("save_profile", MODE_PRIVATE)
                    sharedPreferences.edit().putString("email", profile.email).putString("password", profile.password).apply()
                    startActivity(Intent(this, MainActivity::class.java).putExtra("email", email).putExtra("password", password))
                } else {
                    throw FailedLoginException()
                }
            } catch (e: EmptyFieldException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: FailedLoginException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        linkSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
