package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R

class SuccessActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.success_activity)
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val param = intent.getStringExtra("param")

        val textViewParam = findViewById<TextView>(R.id.textViewParam)
        textViewParam.text = param
        val buttonSuccess = findViewById<Button>(R.id.buttonSuccess)

        buttonSuccess.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("email", email)
                putExtra("password", password)
            })
        }
    }
}