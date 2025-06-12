package com.kleberson.appbanco.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        val balanceCreditTextView = findViewById<TextView>(R.id.textViewBalanceCredit)
        val buttonDeposit = findViewById<Button>(R.id.buttonDeposit)
        val buttonSake = findViewById<Button>(R.id.buttonSake)
        val buttonTransfer = findViewById<Button>(R.id.buttonTransfer)
        val profile = db.login(email, password)

        if (profile != null) {
            nameTextView.text = profile.firstName
            balanceTextView.text = formatBalance.format(profile.balance)
            balanceCreditTextView.text = formatBalance.format(profile.limitCredit)
        }

        buttonDeposit.setOnClickListener {
            Log.d("MainActivity", "Deposit button clicked")
            val intent = Intent(this, DepositActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money deposited successfully")
            startActivity(intent)
        }

        buttonSake.setOnClickListener {
            Log.d("MainActivity", "Sake button clicked")
            val intent = Intent(this, SakeActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money withdrawn successfully")
            startActivity(intent)
        }

        buttonTransfer.setOnClickListener {
            Log.d("MainActivity", "Transfer button clicked")
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money Transfered Successfully")
            startActivity(intent)
        }
    }
}