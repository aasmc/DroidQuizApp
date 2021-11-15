package ru.aasmc.droidquiz.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To3 : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        val createNewTableSql = """
            CREATE TABLE question_new 
            (question_id INTEGER NOT NULL, 
            text TEXT NOT NULL,
            difficulty TEXT NOT NULL,
            PRIMARY KEY (question_id))
        """

        database.execSQL(createNewTableSql)

        val createIndexSql = """
           CREATE INDEX index_question_new_question_id ON question_new(question_id) 
        """
        database.execSQL(createIndexSql)

        val insertIntoNewSql = """
           INSERT INTO question_new (question_id, text, difficulty)
            SELECT question_id, text, difficulty FROM questions
        """
        database.execSQL(insertIntoNewSql)

        val dropTableSql = "DROP TABLE questions"
        database.execSQL(dropTableSql)

        val alterTableSql = """
           ALTER TABLE question_new RENAME TO questions 
        """
        database.execSQL(alterTableSql)
    }
}