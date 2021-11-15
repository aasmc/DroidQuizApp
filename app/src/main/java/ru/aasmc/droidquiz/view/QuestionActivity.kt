package ru.aasmc.droidquiz.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import ru.aasmc.droidquiz.R
import ru.aasmc.droidquiz.data.db.Repository
import ru.aasmc.droidquiz.data.model.QuizState
import ru.aasmc.droidquiz.databinding.ActivityQuestionBinding
import ru.aasmc.droidquiz.getViewModel
import ru.aasmc.droidquiz.viewmodel.QuizViewModel

class QuestionActivity : AppCompatActivity() {

    private val viewModel by lazy {
        getViewModel {
            QuizViewModel(Repository())
        }
    }

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { nextQuestion() }
        getQuestionAndAnswers()
    }

    private fun render(quizState: QuizState) {
        when (quizState) {
            is QuizState.DataState -> renderDataState(quizState)
            is QuizState.EmptyState -> renderEmptyState()
            is QuizState.FinishState -> {
                goToResultActivity(quizState.numberOfQuestions, quizState.score)
            }
            is QuizState.LoadingState -> renderLoadingState()
        }
    }

    private fun renderLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun goToResultActivity(numberOfQuestions: Int, score: Int) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra(SCORE, score)
            putExtra(NUMBER_OF_QUESTIONS, numberOfQuestions)
        }
        startActivity(intent)
    }

    private fun renderEmptyState() {
        binding.progressBar.visibility = View.GONE
        binding.emptyDroid.visibility = View.VISIBLE
        binding.emptyTextView.visibility = View.VISIBLE
    }

    private fun renderDataState(quizState: QuizState.DataState) {
        binding.progressBar.visibility = View.GONE
        displayQuestionView()
        binding.questionsRadioGroup.clearCheck()
        binding.questionTextView.text = quizState.data.question?.text
        binding.questionsRadioGroup.forEachIndexed { index, view ->
            if (index < quizState.data.answers.size) {
                (view as RadioButton).text = quizState.data.answers[index].text
            }
        }
    }

    private fun displayQuestionView() {
        binding.questionsRadioGroup.visibility = View.VISIBLE
        binding.questionTextView.visibility = View.VISIBLE
        binding.button.visibility = View.VISIBLE
    }

    fun nextQuestion() {
        val radioButton =
            findViewById<RadioButton>(binding.questionsRadioGroup.checkedRadioButtonId)
        val selectedOption = binding.questionsRadioGroup.indexOfChild(radioButton)
        if (selectedOption != -1) {
            viewModel.nextQuestion(selectedOption)
        } else {
            Toast.makeText(this, getString(R.string.please_select_an_option), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getQuestionAndAnswers() {
        viewModel.getCurrentState().observe(this) {
            render(it)
        }
    }

    companion object {
        const val SCORE = "SCORE"
        const val NUMBER_OF_QUESTIONS = "NUMBER_OF_QUESTIONS"
    }
}

















