package ru.aasmc.droidquiz.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aasmc.droidquiz.R
import ru.aasmc.droidquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { playAgain() }

        val score = intent.extras?.getInt(QuestionActivity.SCORE)
        val numberOfQuestions = intent.extras?.getInt(QuestionActivity.NUMBER_OF_QUESTIONS)
        binding.scoreTextView.text = String.format(getString(R.string.score_message), score, numberOfQuestions)
    }

    private fun playAgain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        startActivity(intent)
    }
}