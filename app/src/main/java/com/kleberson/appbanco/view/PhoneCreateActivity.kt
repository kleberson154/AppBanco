package com.kleberson.appbanco.view

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.exception.EmptyFieldException
import com.kleberson.appbanco.exception.FailedLoginException

class PhoneCreateActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_create_activity)
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val firstName = intent.getStringExtra("firstName") ?: ""
        val lastName = intent.getStringExtra("lastName") ?: ""

        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonPhone = findViewById<Button>(R.id.buttonPhone)

        val accountController = AccountController(this)

        buttonPhone.setOnClickListener {
            try{
                val phone = editTextPhone.text.toString()

                if (phone.isBlank()) {
                    throw EmptyFieldException()
                }

                accountController.createAccount(email, password, firstName, lastName, phone)
                if (accountController.login(email, password) != null) {
                    val intent = Intent(this, MainActivity::class.java).putExtra("email", email)
                        .putExtra("password", password)
                    startActivity(intent)
                } else {
                    throw FailedLoginException()
                }
            }catch (e: EmptyFieldException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }catch (e: FailedLoginException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}