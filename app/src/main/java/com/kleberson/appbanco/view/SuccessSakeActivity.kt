package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R

class SuccessSakeActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sake_activity)
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        val buttonSuccess = findViewById<Button>(R.id.buttonSuccessSake)

        buttonSuccess.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("email", email)
                putExtra("password", password)
            })
        }
    }
}