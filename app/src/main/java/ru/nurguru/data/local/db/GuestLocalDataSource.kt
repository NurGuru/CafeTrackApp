package ru.nurguru.data.local.db

import android.content.ContentValues
import ru.nurguru.data.model.GuestEntity
import ru.nurguru.data.safeCallReadable
import ru.nurguru.data.safeCallWritable

class GuestLocalDataSource(private val database: Database) {

//добавить гостя СДЕЛАЛ
    fun addGuest(guest: GuestEntity) {

        database.safeCallWritable {
            val values = ContentValues().apply {
                put(GuestTable.Columns.GUEST_NAME, guest.name)
                put(GuestTable.Columns.PHONE, guest.number)
                put(GuestTable.Columns.VISITS_COUNT, guest.visits)
                put(GuestTable.Columns.MONEY_SPENT, guest.moneySpent)
                put(GuestTable.Columns.STATUS, guest.statusStr)
                put(GuestTable.Columns.DISCOUNT, guest.discountStr)
            }

            it.insert(GuestTable.tableName, null, values)
        }
    }

    fun updateGuest(guest: GuestEntity, id: Int) {

        database.safeCallWritable {
            val values = ContentValues().apply {
                put(GuestTable.Columns.GUEST_NAME, guest.name)
                put(GuestTable.Columns.PHONE, guest.number)
                put(GuestTable.Columns.VISITS_COUNT, guest.visits)
                put(GuestTable.Columns.MONEY_SPENT, guest.moneySpent)
                put(GuestTable.Columns.STATUS, guest.statusStr)
                put(GuestTable.Columns.DISCOUNT, guest.discountStr)
            }
            it.update(
                GuestTable.tableName, values, "${GuestTable.Columns.GUEST_ID} = ?",
                arrayOf(id.toString())
            )
        }
    }

    fun deleteGuest(id: Int) {
        database.writableDatabase.apply {
            delete(
                GuestTable.tableName,
                "${GuestTable.Columns.GUEST_ID} = ?",
                arrayOf(id.toString())
            )
        }
    }

    fun getGuests(): List<GuestEntity> {
        val guestsList = mutableListOf<GuestEntity>()

        database.safeCallReadable {
            it.query(
                GuestTable.tableName,
                null,
                null,
                null,
                null,
                null,
                null,
            ).use { cursor ->
                while (cursor.moveToNext()) {

                    with(cursor) {
                        val guest = GuestEntity(
                            id = getInt(getColumnIndexOrThrow(GuestTable.Columns.GUEST_ID)),
                            name = getString(getColumnIndexOrThrow(GuestTable.Columns.GUEST_NAME)),
                            number = getLong(getColumnIndexOrThrow(GuestTable.Columns.PHONE)),
                            visits = getInt(getColumnIndexOrThrow(GuestTable.Columns.VISITS_COUNT)),
                            moneySpent = getInt(getColumnIndexOrThrow(GuestTable.Columns.MONEY_SPENT)),
                            statusStr = getString(getColumnIndexOrThrow(GuestTable.Columns.STATUS)),
                            discountStr = getString(getColumnIndexOrThrow(GuestTable.Columns.DISCOUNT))
                        )
                        guestsList.add(guest)
                    }
                }
            }
        }
        return guestsList
    }
}