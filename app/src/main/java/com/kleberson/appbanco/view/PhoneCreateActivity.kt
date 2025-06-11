package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.exception.EmptyFieldException
import com.kleberson.appbanco.exception.FailedLoginException

class PhoneCreateActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_create_activity)

        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonPhone = findViewById<Button>(R.id.buttonPhone)

        val accountController = AccountController(this)
        val db = Database(this)

        buttonPhone.setOnClickListener {
            try{
                val phone = editTextPhone.text.toString()

                if (phone.isBlank()) {
                    throw EmptyFieldException("Todos os campos devem ser preenchidos.")
                }

                getSharedPreferences("save_profile", MODE_PRIVATE).edit().putString("phone", phone).apply()
                val sharedPref = this.getSharedPreferences("save_profile", MODE_PRIVATE)
                accountController.createAccount(sharedPref.getString("email", "") ?: "",
                    sharedPref.getString("password", "") ?: "",
                    sharedPref.getString("firstName", "") ?: "",
                    sharedPref.getString("lastName", "") ?: "",
                    phone)
                if (db.login(sharedPref.getString("email", "") ?: "",
                        sharedPref.getString("password", "") ?: "") != null) {
                    startActivity(Intent(this, MainActivity::class.java).putExtra("email", sharedPref.getString("email", "")).putExtra("password", sharedPref.getString("password", "")))
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