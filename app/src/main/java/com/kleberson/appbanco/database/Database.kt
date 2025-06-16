package com.kleberson.appbanco.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.kleberson.appbanco.model.Account

class Database(context: Context) : SQLiteOpenHelper(
    context,
    "person.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableAccount = "CREATE TABLE IF NOT EXISTS account(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "firstName TEXT NOT NULL, " +
                "lastName TEXT NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "balance REAL DEFAULT 0.0, " +
                "limitCredit REAL DEFAULT 1000.0" +
                ");"
        db?.execSQL(createTableAccount)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertAccount(
        accountUser: Account
    ) {
        val db = writableDatabase
        val sql = "INSERT INTO account (email, password, firstName, lastName, phone) VALUES (?, ?, ?, ?, ?)"
        db.execSQL(sql, arrayOf(accountUser.email, accountUser.password, accountUser.firstName, accountUser.lastName, accountUser.phone))
    }

    fun searchUser(email: String): Boolean {
        val db = readableDatabase
        val sql = "SELECT * FROM account WHERE email = ?"
        val cursor = db.rawQuery(sql, arrayOf(email))
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    @SuppressLint("Range")
    fun login(email: String, password: String): Account? {
        val db = readableDatabase
        val sql = "SELECT * FROM account WHERE email = ? AND password = ?"
        val cursor = db.rawQuery(sql, arrayOf(email, password))
        return if (cursor.moveToFirst()) {
            val firstName = cursor.getString(cursor.getColumnIndex("firstName"))
            val lastName = cursor.getString(cursor.getColumnIndex("lastName"))
            val phone = cursor.getString(cursor.getColumnIndex("phone"))
            val balance = cursor.getDouble(cursor.getColumnIndex("balance"))
            val limitCredit = cursor.getDouble(cursor.getColumnIndex("limitCredit"))
            cursor.close()
            Account(email, password, firstName, lastName, phone, balance, limitCredit)
        } else {
            cursor.close()
            null
        }
    }

    fun deposit(email: String, value: Double) {
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM account WHERE email = ?", arrayOf(email))

        if (cursor.moveToFirst()) {
            val sql = "UPDATE account SET balance = balance + ? WHERE email = ?"
            db.execSQL(sql, arrayOf(value, email))
            cursor.close()
        }
    }

    @SuppressLint("Range", "Recycle")
    fun sake(email: String, value: Double) {
        val db = writableDatabase
        var sql = "SELECT * FROM account WHERE email = ?"
        val cursor = db.rawQuery(sql, arrayOf(email))
        if(cursor.moveToFirst()){
            val balance = cursor.getDouble(cursor.getColumnIndex("balance"))
            val limitCredit = cursor.getDouble(cursor.getColumnIndex("limitCredit"))
            if (balance + limitCredit > value) {
                sql = "UPDATE account SET balance = balance - ? WHERE email = ?"
            }
            db.execSQL(sql, arrayOf(value, email))
        } else {
            cursor.close()
            return
        }
    }

    fun transfer(email: String, value: Double, inputEmail: String) {
        sake(email, value)
        deposit(inputEmail, value)
    }
}