package ru.nurguru.data

import android.database.sqlite.SQLiteDatabase
import ru.nurguru.data.local.db.Database

inline fun <T> Database.safeCallWritable(action: (SQLiteDatabase) -> T) {
    action.invoke(writableDatabase)
    close()
}

inline fun <T> Database.safeCallReadable(action: (SQLiteDatabase) -> T) {
    action.invoke(readableDatabase)
    close()
}
