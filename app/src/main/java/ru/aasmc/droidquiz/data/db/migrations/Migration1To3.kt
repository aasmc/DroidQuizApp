package ru.aasmc.droidquiz.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To3 : Migration(1, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        val alterTableSql = """
           ALTER TABLE questions ADD COLUMN difficulty TEXT NOT NULL DEFAULT 0 
        """
        database.execSQL(alterTableSql)
    }
}