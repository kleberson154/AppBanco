package com.kleberson.appbanco.view

import android.annotation.SuppressLint
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
        val email = getSharedPreferences("save_profile", MODE_PRIVATE).getString("email", "") ?: ""

        val nameTextView = findViewById<TextView>(R.id.textViewShowNameMain)
        val balanceTextView = findViewById<TextView>(R.id.textViewBalance)
        val account = db.getAccountByEmail(email)
        val formatBalance = FormatBalance(account?.balance ?: 0.0)
        if (account != null) {
            nameTextView.text = account.firstName
        }
        balanceTextView.text = account?.let {
            formatBalance.format()
        } ?: "0,00"
    }
}