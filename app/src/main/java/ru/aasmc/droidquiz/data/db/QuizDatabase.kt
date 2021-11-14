package ru.aasmc.droidquiz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aasmc.droidquiz.data.model.Answer
import ru.aasmc.droidquiz.data.model.Question

@Database(entities = [Question::class, Answer::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {

}