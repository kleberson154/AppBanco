package com.kleberson.appbanco.view

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.exception.EmptyFieldException

class ProfileCreateActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_create_activity)
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        val firstName = findViewById<EditText>(R.id.editTextFirstName)
        val lastName = findViewById<EditText>(R.id.editTextLastName)
        val buttonSet = findViewById<Button>(R.id.buttonSet)

        val accountController = AccountController(this)

        buttonSet.setOnClickListener {
            try{
                val firstNameText = firstName.text.toString()
                val lastNameText = lastName.text.toString()

                if (firstNameText.isBlank() || lastNameText.isBlank()) {
                    throw EmptyFieldException("Todos os campos devem ser preenchidos.")
                }
                val intent = Intent(this, PhoneCreateActivity::class.java).putExtra("email", email)
                    .putExtra("password", password)
                    .putExtra("firstName", firstNameText)
                    .putExtra("lastName", lastNameText)
                startActivity(intent)
            }catch (e: EmptyFieldException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}