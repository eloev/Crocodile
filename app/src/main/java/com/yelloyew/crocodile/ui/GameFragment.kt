package com.yelloyew.crocodile.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.android.yelloyew.crocodile.R
import com.android.yelloyew.crocodile.databinding.FragmentGameBinding
import com.yelloyew.crocodile.ViewModel

class GameFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()

    private lateinit var _binding: FragmentGameBinding
    private val binding get() = _binding

    private var words: MutableList<String> = mutableListOf()
    private var string: String = ""

    private lateinit var timer: CountDownTimer
    private var timerRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        updateWords()
        pressToCard()
        binding.gameText.setOnClickListener {
            pressToCard()
        }
        return binding.root
    }

    private fun pressToCard() {
        if (!viewModel.pressToCard) {
            updateWords()
            viewModel.pressToCard = true
        } else {
            binding.gameText.text = getString(R.string.press_to_card)
            viewModel.pressToCard = false
            if (timerRunning) {
                stopTimer()
            }
        }
    }

    private fun createWords() {
        // создаём слова
        when (viewModel.gameController) {
            1 -> {
                string = getString(R.string.words1)
            }
            2 -> {
                string = getString(R.string.words2)
            }
            3 -> {
                string = getString(R.string.words3)
            }
            4 -> {
                string = getString(R.string.words4)
            }
        }
        words = string.split(" ").toMutableList()
    }

    private fun updateWords() {
        /*1 - популярные слова, 2 - серьёзная игра, 3 - 18+, 4 - хардкор, 5 - правила */
        // выводим слова
        if (!words.isNullOrEmpty()) {
            if (viewModel.gameController == 5) {
                //правила
                binding.gameText.text = getString(R.string.rules_info)
                stopTimer()

            } else {
                //слова
                stopTimer()
                val rand = (1..words.size).random() - 1
                binding.gameText.text = words[rand]
                words.removeAt(rand)
                startTimer()
                if (words.size == 0) {
                    binding.gameText.text = getString(R.string.end_game)
                    createWords()
                    stopTimer()
                }
            }
        } else createWords()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(viewModel.timerCount, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.currentTimer.isVisible = true
                val minutes = (millisUntilFinished / 60000L).toInt()
                var seconds = ((millisUntilFinished - (minutes * 60000)) / 1000).toString()
                if (seconds.length == 1) {
                    seconds = "0$seconds"
                }
                binding.currentTimer.text =
                    (getString(R.string.end_time) + " " + minutes.toString() + ":" + seconds)
            }

            override fun onFinish() {
                binding.currentTimer.isVisible = false
                binding.gameText.text = (getString(R.string.end) + " " + binding.gameText.text)
            }
        }
        timer.start()
        timerRunning = true
    }

    private fun stopTimer() {
        binding.currentTimer.isVisible = false
        if (timerRunning){
            timer.cancel()
        }

        timerRunning = false
    }

    override fun onPause() {
        super.onPause()
        stopTimer()
    }
}