package ru.aasmc.droidquiz.data.db

import androidx.lifecycle.LiveData
import ru.aasmc.droidquiz.QuizApplication
import ru.aasmc.droidquiz.data.QuizRepository
import ru.aasmc.droidquiz.data.model.Answer
import ru.aasmc.droidquiz.data.model.Question
import ru.aasmc.droidquiz.data.model.QuestionAndAllAnswers

class Repository : QuizRepository {

    private val quizDao: QuizDao by lazy {
        QuizApplication.database.quizDao()
    }

    private val allQuestions by lazy {
        quizDao.getAllQuestion()
    }

    private val allQuestionsAndAllAnswers by lazy {
        quizDao.getQuestionAndAllAnswers()
    }

    override fun getSavedQuestions(): LiveData<List<Question>> = allQuestions

    override suspend fun saveQuestion(question: Question) {
        quizDao.insert(question)
    }

    override suspend fun saveAnswer(answer: Answer) {
        quizDao.insert(answer)
    }

    override fun getQuestionAndAllAnswers(): LiveData<List<QuestionAndAllAnswers>> =
        allQuestionsAndAllAnswers

    override suspend fun deleteQuestions() {
        quizDao.clearQuestion()
    }
}