package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.util.FormatBalance

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val db = Database(this)
        val formatBalance = FormatBalance()
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        val nameTextView = findViewById<TextView>(R.id.textViewShowNameMain)
        val balanceTextView = findViewById<TextView>(R.id.textViewBalance)
        val profile = db.login(email, password)

        if (profile != null) {
            nameTextView.text = profile.firstName
            balanceTextView.text = formatBalance.format(profile.balance)
        }
    }
}