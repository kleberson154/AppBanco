package com.kleberson.appbanco.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
                "phone TEXT NOT NULL" +
                ");"
        db?.execSQL(createTableAccount)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertAccount(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phone: String
    ) {
        val db = writableDatabase
        val sql = "INSERT INTO person (email, password, firstName, lastName, phone) VALUES (?, ?, ?, ?, ?)"
        db.execSQL(sql, arrayOf(email, password, firstName, lastName, phone))
        db.close()
    }
}