package ru.nurguru.data.local.db

abstract class DatabaseTable(val tableName: String) {

    abstract fun createTableQuery(): String

    fun deleteTableQuery() = "DROP TABLE IF EXIST $tableName"
}

object GuestTable : DatabaseTable(tableName = "guests") {

    object Columns {
        const val GUEST_ID = "_id"
        const val GUEST_NAME = "name"
        const val PHONE = "number"
        const val VISITS_COUNT = "visits"
        const val MONEY_SPENT = "moneySpent"
        const val STATUS = "status"
        const val DISCOUNT = "discount"
    }

    override fun createTableQuery(): String = " CREATE TABLE $tableName (" +
            "${Columns.GUEST_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Columns.GUEST_NAME} TEXT," +
            "${Columns.PHONE} LONG," +
            "${Columns.VISITS_COUNT} INTEGER," +
            "${Columns.MONEY_SPENT} INTEGER," +
            "${Columns.STATUS} TEXT," +
            "${Columns.DISCOUNT} TEXT);"
}

object MenuTable : DatabaseTable(tableName = "menu") {

    object Columns {
        const val PRODUCT_ID = "_id"
        const val PRODUCT_IMG = "productImage"
        const val PRODUCT_NAME = "productName"
        const val CATEGORY = "category"
        const val PRICE = "price"
        const val PRIME_COST = "primeCost"
    }

    override fun createTableQuery(): String = " CREATE TABLE $tableName (" +
            "${Columns.PRODUCT_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Columns.PRODUCT_IMG} TEXT," +
            "${Columns.PRODUCT_NAME} TEXT," +
            "${Columns.CATEGORY} TEXT," +
            "${Columns.PRICE} INTEGER," +
            "${Columns.PRIME_COST} INTEGER);"
}

object CategoryTable : DatabaseTable(tableName = "category") {

    object Columns {
        const val CATEGORY_ID = "_id"
        const val CATEGORY_IMG = "categoryImage"
        const val CATEGORY_NAME = "categoryName"
    }

    override fun createTableQuery(): String = " CREATE TABLE $tableName (" +
            "${Columns.CATEGORY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Columns.CATEGORY_IMG} TEXT," +
            "${Columns.CATEGORY_NAME} TEXT);"
}