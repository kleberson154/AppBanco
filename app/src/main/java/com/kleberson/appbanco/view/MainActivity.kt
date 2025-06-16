package com.kleberson.appbanco.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kleberson.appbanco.R
import com.kleberson.appbanco.controller.AccountController
import com.kleberson.appbanco.util.FormatBalance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val accountController = AccountController(this)
        val formatBalance = FormatBalance()
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        val nameTextView = findViewById<TextView>(R.id.textViewShowNameMain)
        val balanceTextView = findViewById<TextView>(R.id.textViewBalance)
        val balanceCreditTextView = findViewById<TextView>(R.id.textViewBalanceCredit)
        val buttonDeposit = findViewById<Button>(R.id.buttonDeposit)
        val buttonSake = findViewById<Button>(R.id.buttonSake)
        val buttonTransfer = findViewById<Button>(R.id.buttonTransfer)
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        val profile = accountController.login(email, password)

        if (profile != null) {
            nameTextView.text = profile.firstName
            balanceTextView.text = formatBalance.format(profile.balance)
            if (profile.balance > 0){
                balanceCreditTextView.text = formatBalance.format(profile.limitCredit)
            } else {
                balanceCreditTextView.text = formatBalance.format(profile.limitCredit + profile.balance)
            }
        }

        buttonDeposit.setOnClickListener {
            val intent = Intent(this, DepositActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money deposited successfully")
            startActivity(intent)
        }

        buttonSake.setOnClickListener {
            val intent = Intent(this, SakeActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money withdrawn successfully")
            startActivity(intent)
        }

        buttonTransfer.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("param", "Money Transfered Successfully")
            startActivity(intent)
        }

        buttonExit.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}