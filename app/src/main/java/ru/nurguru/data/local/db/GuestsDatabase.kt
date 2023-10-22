package ru.nurguru.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuestsDatabase(context: Context, private val tables: List<DatabaseTable>) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(GuestTable.createTableQuery())
        db?.execSQL(MenuTable.createTableQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(GuestTable.deleteTableQuery())
        db?.execSQL(MenuTable.deleteTableQuery())
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "DB.db"
    }

}
