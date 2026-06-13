package com.example.propertytaxcalculator

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "property_tax.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "property_tax"

        const val COL_ID = "id"
        const val COL_OWNER = "owner_name"
        const val COL_TYPE = "property_type"
        const val COL_AREA = "area"
        const val COL_AGE = "building_age"
        const val COL_OCCUPANCY = "occupancy"
        const val COL_ZONE = "zone"
        const val COL_TAX = "tax_amount"
        const val COL_DATE = "created_date"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val query = """
            CREATE TABLE $TABLE_NAME(
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_OWNER TEXT,
                $COL_TYPE TEXT,
                $COL_AREA REAL,
                $COL_AGE TEXT,
                $COL_OCCUPANCY TEXT,
                $COL_ZONE TEXT,
                $COL_TAX REAL,
                $COL_DATE TEXT
            )
        """.trimIndent()

        db.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTax(propertyTax: PropertyTax): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put(COL_OWNER, propertyTax.ownerName)
            put(COL_TYPE, propertyTax.propertyType)
            put(COL_AREA, propertyTax.area)
            put(COL_AGE, propertyTax.buildingAge)
            put(COL_OCCUPANCY, propertyTax.occupancy)
            put(COL_ZONE, propertyTax.zone)
            put(COL_TAX, propertyTax.taxAmount)
            put(COL_DATE, propertyTax.createdDate)
        }

        val result = db.insert(TABLE_NAME, null, values)

        db.close()

        return result != -1L
    }

    fun getAllRecords(): ArrayList<PropertyTax> {

        val list = ArrayList<PropertyTax>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME ORDER BY $COL_ID DESC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val propertyTax = PropertyTax(
                    ownerName = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_OWNER)
                    ),
                    propertyType = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_TYPE)
                    ),
                    area = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(COL_AREA)
                    ),
                    buildingAge = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_AGE)
                    ),
                    occupancy = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_OCCUPANCY)
                    ),
                    zone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZONE)
                    ),
                    taxAmount = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(COL_TAX)
                    ),
                    createdDate = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATE)
                    )
                )

                list.add(propertyTax)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return list
    }

    fun getTotalProperties(): Int {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM $TABLE_NAME",
            null
        )

        var count = 0

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }

        cursor.close()
        db.close()

        return count
    }

    fun getTotalTaxCollected(): Double {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT SUM($COL_TAX) FROM $TABLE_NAME",
            null
        )

        var total = 0.0

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0)
        }

        cursor.close()
        db.close()

        return total
    }

    fun getHighestTax(): Double {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT MAX($COL_TAX) FROM $TABLE_NAME",
            null
        )

        var highest = 0.0

        if (cursor.moveToFirst()) {
            highest = cursor.getDouble(0)
        }

        cursor.close()
        db.close()

        return highest
    }

    fun getLowestTax(): Double {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT MIN($COL_TAX) FROM $TABLE_NAME",
            null
        )

        var lowest = 0.0

        if (cursor.moveToFirst()) {
            lowest = cursor.getDouble(0)
        }

        cursor.close()
        db.close()

        return lowest
    }

    fun getAverageTax(): Double {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT AVG($COL_TAX) FROM $TABLE_NAME",
            null
        )

        var average = 0.0

        if (cursor.moveToFirst()) {
            average = cursor.getDouble(0)
        }

        cursor.close()
        db.close()

        return average
    }
}