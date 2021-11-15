package ru.aasmc.droidquiz.data

import androidx.lifecycle.LiveData
import ru.aasmc.droidquiz.data.model.Answer
import ru.aasmc.droidquiz.data.model.Question
import ru.aasmc.droidquiz.data.model.QuestionAndAllAnswers

interface QuizRepository {

    fun getSavedQuestions(): LiveData<List<Question>>

    suspend fun saveQuestion(question: Question)

    suspend fun saveAnswer(answer: Answer)

    fun getQuestionAndAllAnswers(): LiveData<List<QuestionAndAllAnswers>>

    suspend fun deleteQuestions()
}