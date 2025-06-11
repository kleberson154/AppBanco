package com.kleberson.appbanco.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController

class DepositActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.deposit_activity)
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        val buttonNextDeposit = findViewById<Button>(R.id.buttonNextDeposit)
        val inputDeposit = findViewById<EditText>(R.id.editTextInputDeposit)
        val accountController = AccountController(this)

        buttonNextDeposit.setOnClickListener {
            accountController.deposit(email, inputDeposit.text.toString().toDoubleOrNull() ?: 0.0)
            startActivity(Intent(this, SuccessDepositActivity::class.java).apply {
                putExtra("email", email)
                putExtra("password", password)
            })
        }
    }
}