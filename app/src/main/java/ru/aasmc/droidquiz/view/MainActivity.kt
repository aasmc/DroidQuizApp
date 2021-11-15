package ru.aasmc.droidquiz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ru.aasmc.droidquiz.R
import ru.aasmc.droidquiz.data.db.Repository
import ru.aasmc.droidquiz.databinding.ActivityMainBinding
import ru.aasmc.droidquiz.getViewModel
import ru.aasmc.droidquiz.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        getViewModel { MainViewModel(Repository()) }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener { goToQuestionActivity() }
    }

    private fun goToQuestionActivity() {
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun prepopulateQuestions() =
        viewModel.prepopulateQuestions()

    private fun clearQuestions() = viewModel.clearQuestions()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.prepopulate -> prepopulateQuestions()
            R.id.clear -> clearQuestions()
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


























