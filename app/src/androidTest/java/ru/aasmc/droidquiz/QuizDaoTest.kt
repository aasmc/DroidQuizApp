package ru.aasmc.droidquiz

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import ru.aasmc.droidquiz.data.db.QuestionInfoProvider
import ru.aasmc.droidquiz.data.db.QuizDao
import ru.aasmc.droidquiz.data.db.QuizDatabase
import ru.aasmc.droidquiz.data.model.Question

@RunWith(AndroidJUnit4::class)
class QuizDaoTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: QuizDatabase
    private lateinit var quizDao: QuizDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context

        try {
            database = Room.inMemoryDatabaseBuilder(
                context,
                QuizDatabase::class.java
            ).allowMainThreadQueries()
                .build()

        } catch (e: Exception) {
            Log.i(
                this.javaClass.simpleName,
                e.message ?: "Unknown error while creating an in memory database"
            )
        }
        quizDao = database.quizDao()
    }

    @Test
    fun testInsertQuestion() {
        val previousNumberOfQuestions = quizDao.getAllQuestion().size

        val quizQuestion = Question(1, "What is your name?")
        quizDao.insert(quizQuestion)

        val newNumberOfQuestions = quizDao.getAllQuestion().size
        val changeInQuestions = newNumberOfQuestions - previousNumberOfQuestions
        assertEquals(1, changeInQuestions)
    }

    @Test
    fun testClearQuestions() {
        for (question in QuestionInfoProvider.questionList) {
            quizDao.insert(question)
        }
        assertTrue(quizDao.getAllQuestion().isNotEmpty())
        quizDao.clearQuestion()
        assertTrue(quizDao.getAllQuestion().isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }

}
























