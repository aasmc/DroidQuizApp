package ru.aasmc.droidquiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aasmc.droidquiz.data.QuizRepository
import ru.aasmc.droidquiz.data.model.QuestionAndAllAnswers
import ru.aasmc.droidquiz.data.model.QuizState

class QuizViewModel(
    repository: QuizRepository
) : ViewModel() {

    private val questionAndAnswers = MediatorLiveData<QuestionAndAllAnswers>()
    private val currentQuestion = MutableLiveData<Int>()
    private val currentState = MediatorLiveData<QuizState>()
    private val allQuestionAndAllAnswers = repository.getQuestionAndAllAnswers()
    private var score: Int = 0

    init {
        currentState.postValue(QuizState.LoadingState)
        addStateSources()
        addQuestionSources()
        currentQuestion.postValue(0)
    }

    fun getCurrentState(): LiveData<QuizState> = currentState

    private fun changeCurrentQuestion() {
        currentQuestion.postValue(currentQuestion.value?.inc())
    }

    private fun addStateSources() {
        currentState.addSource(currentQuestion) { currentQuestionNumber ->
            if (currentQuestionNumber == allQuestionAndAllAnswers.value?.size) {
                currentState.postValue(QuizState.FinishState(currentQuestionNumber, score))
            }
        }
        currentState.addSource(allQuestionAndAllAnswers) { questionAndAllAnswers ->
            if (questionAndAllAnswers.isEmpty()) {
                currentState.postValue(QuizState.EmptyState)
            }
        }

        currentState.addSource(questionAndAnswers) { qAndA ->
            currentState.postValue(QuizState.DataState(qAndA))
        }
    }

    private fun addQuestionSources() {
        questionAndAnswers.addSource(currentQuestion) { currentQuestionNumber ->
            val questions = allQuestionAndAllAnswers.value
            if (questions != null && currentQuestionNumber < questions.size) {
                questionAndAnswers.postValue(questions[currentQuestionNumber])
            }
        }

        questionAndAnswers.addSource(allQuestionAndAllAnswers) { qAndA ->
            val currentQuestionNumber = currentQuestion.value
            if (currentQuestionNumber != null && qAndA.isNotEmpty()) {
                questionAndAnswers.postValue(qAndA[currentQuestionNumber])
            }
        }
    }

    fun nextQuestion(choice: Int) {
        verifyAnswer(choice)
        changeCurrentQuestion()
    }

    private fun verifyAnswer(choice: Int) {
        val currentQuestion = questionAndAnswers.value
        if (currentQuestion != null && currentQuestion.answers[choice].isCorrect) {
            ++score
        }
    }

}























