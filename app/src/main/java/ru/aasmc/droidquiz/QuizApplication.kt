package ru.aasmc.droidquiz

import android.app.Application
import androidx.room.Room
import ru.aasmc.droidquiz.data.db.QuizDatabase

class QuizApplication : Application() {
    private val DB_NAME = "quiz_database.db"

    companion object {
        lateinit var database: QuizDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            QuizDatabase::class.java,
            DB_NAME
        )
            .addMigrations(
                QuizDatabase.MIGRATION_1_TO_2,
                QuizDatabase.MIGRATION_2_TO_3,
                QuizDatabase.MIGRATION_1_TO_3
            )
            .build()
    }
}