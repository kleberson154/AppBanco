package com.kleberson.appbanco.view

import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController

class ProfileCreateActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_create_activity)

        val firstName = findViewById<EditText>(R.id.editTextFirstName)
        val lastName = findViewById<EditText>(R.id.editTextLastName)
        val buttonSet = findViewById<Button>(R.id.buttonSet)

        val accountController = AccountController(this)

        buttonSet.setOnClickListener {
            val firstNameText = firstName.text.toString()
            val lastNameText = lastName.text.toString()
            accountController.setName(firstNameText, lastNameText)
        }
    }
}