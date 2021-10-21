package com.yelloyew.crocodile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.yelloyew.crocodile.R
import com.yelloyew.crocodile.databinding.ActivityCrocodileBinding

class CrocodileActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCrocodileBinding
    private val binding get() = _binding

    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCrocodileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.timerCount.text =
            ((viewModel.timerCount / 60000L).toString() + " " + getString(R.string.timer_count))
        binding.timerTouch.setOnClickListener {
            updateTimer()
        }
        binding.gamersTouch.setOnClickListener {
            showToast(getString(R.string.max_gamers))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val rulesItem: MenuItem = menu.findItem(R.id.menu_item_rules)
        rulesItem.setOnMenuItemClickListener {
            if (viewModel.gameController == 5) {
                viewModel.gameController = viewModel.pastGameController
                viewModel.pressToCard = !viewModel.pastPressToCard
                onBackPressed()
            } else {
                viewModel.pastGameController = viewModel.gameController
                viewModel.pastPressToCard = viewModel.pressToCard
                viewModel.pressToCard = false
                viewModel.gameController = 5
                findNavController(R.id.host_fragment).navigate(R.id.gameFragment)
            }
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun updateTimer() {
        if (viewModel.timerCount < 300000L) {
            viewModel.timerCount += 60000L
        } else {
            viewModel.timerCount = 120000L
        }
        binding.timerCount.text =
            ((viewModel.timerCount / 60000L).toString() + " " + getString(R.string.timer_count))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}