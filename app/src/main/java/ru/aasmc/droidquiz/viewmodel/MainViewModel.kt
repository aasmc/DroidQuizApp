package ru.aasmc.droidquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.aasmc.droidquiz.data.QuizRepository
import ru.aasmc.droidquiz.data.db.QuestionInfoProvider
import ru.aasmc.droidquiz.data.model.Question

class MainViewModel(
    private val repository: QuizRepository
) : ViewModel() {

    fun prepopulateQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            for (question in QuestionInfoProvider.questionList) {
                repository.saveQuestion(question)
            }
            for (answer in QuestionInfoProvider.answerList) {
                repository.saveAnswer(answer)
            }
        }
    }

    fun clearQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteQuestions()
        }
    }

}