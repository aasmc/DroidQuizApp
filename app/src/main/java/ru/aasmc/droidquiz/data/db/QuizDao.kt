package ru.aasmc.droidquiz.data.db

import androidx.room.*
import ru.aasmc.droidquiz.data.model.Answer
import ru.aasmc.droidquiz.data.model.Question
import ru.aasmc.droidquiz.data.model.QuestionAndAllAnswers

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(answer: Answer)

    @Query("DELETE FROM questions")
    fun clearQuestion()

    @Delete
    fun delete(question: Question)

    @Query("SELECT * FROM questions ORDER BY question_id")
    fun getAllQuestion(): List<Question>

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionAndAllAnswers(): List<QuestionAndAllAnswers>

}