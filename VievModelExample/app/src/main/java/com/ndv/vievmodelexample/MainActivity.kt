package com.ndv.vievmodelexample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rollCountTextView: TextView
    private lateinit var diceTypeEditText: EditText
    private lateinit var rollDiceButton: Button
    private lateinit var rollResultTextView: TextView
    private val viewModel: DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupElements()

        setupRollDiceButton()
        setupRollsCount()
        setupResultAlertDialog()
    }

    private fun setupElements() {
        rollCountTextView = findViewById(R.id.rollsCount)
        diceTypeEditText = findViewById(R.id.diceType)
        rollDiceButton = findViewById(R.id.actionButton)
        rollResultTextView = findViewById(R.id.rollResult)
    }

    private fun setupRollDiceButton() {
        rollDiceButton.setOnClickListener {
            val dice = diceTypeEditText.text.toString()

            if (dice == "") {
                diceTypeEditText.error = getString(R.string.emptyError)
            } else {
                viewModel.rollDice(dice.toInt())
            }
        }
    }

    private fun setupRollsCount() {
        viewModel.rollsCount.observe(this) { count ->
            rollCountTextView.text = getString(R.string.rollsCount, count)
        }
    }

    private fun setupResultAlertDialog() {
        viewModel.rollResult.observe(this) { result ->
            rollResultTextView.text = result.toString()
        }
    }
}