package ru.nurguru.data

import android.database.sqlite.SQLiteDatabase
import ru.nurguru.data.local.db.GuestsDatabase

inline fun <T> GuestsDatabase.safeCallWritable(action: (SQLiteDatabase) -> T) {
    action.invoke(writableDatabase)
    close()
}

inline fun <T> GuestsDatabase.safeCallReadable(action: (SQLiteDatabase) -> T) {
    action.invoke(readableDatabase)
    close()
}
