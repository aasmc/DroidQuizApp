package ru.aasmc.droidquiz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aasmc.droidquiz.data.db.migrations.Migration1To2
import ru.aasmc.droidquiz.data.db.migrations.Migration2To3
import ru.aasmc.droidquiz.data.model.Answer
import ru.aasmc.droidquiz.data.model.Question

@Database(entities = [(Question::class), (Answer::class)], version = 3, exportSchema = true)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        val MIGRATION_1_TO_2 = Migration1To2()
        val MIGRATION_2_TO_3 = Migration2To3()
    }
}