package com.ndv.vievmodelexample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var rollCountTextView: TextView
    private lateinit var diceTypeEditText: EditText
    private lateinit var rollDiceButton: Button
    private val viewModel: DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollCountTextView = findViewById(R.id.rollsCount)
        diceTypeEditText = findViewById(R.id.diceType)
        rollDiceButton = findViewById(R.id.actionButton)

        setupRollDiceButton()
        setupRollsCount()
    }

    private fun setupRollDiceButton() {
        rollDiceButton.setOnClickListener {
            val dice = diceTypeEditText.text.toString()

            if (dice == "") {
                diceTypeEditText.error = getString(R.string.emptyError)
            } else {
                viewModel.rollDice(dice.toInt())
                viewModel.rollResult.toString()

                setupRollsCount()
                showResultRollDialog()
            }
        }
    }

    private fun setupRollsCount() {
        viewModel.rollsCount.observe(this) { count -> rollCountTextView.text = count.toString() }
    }

    private fun showResultRollDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.alert_message, viewModel.rollResult))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}