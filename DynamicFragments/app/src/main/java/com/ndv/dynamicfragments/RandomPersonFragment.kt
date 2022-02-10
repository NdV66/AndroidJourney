package com.ndv.dynamicfragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


const val DELAY_MS: Long = 100
const val CURRENT_NAME = "CURRENT_NAME"
const val WAS_NAME_RUNNING = "WAS_NAME_RUNNING"
const val IS_NAME_RUNNING = "IS_NAME_RUNNING"
const val DECISION_TIME = "DECISION_TIME"
const val REPLACE_WITH_STARS = false

class RandomPersonFragment : Fragment() {
    val mainHandler: Handler = Handler(Looper.getMainLooper())
    private var currentName: String = ""
    private var isNameRunning = false
    private var wasNameRunning = false
    private var decisionTimeSeconds = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            currentName = savedInstanceState.getString(CURRENT_NAME) ?: ""
            isNameRunning = savedInstanceState.getBoolean(IS_NAME_RUNNING)
            wasNameRunning = savedInstanceState.getBoolean(WAS_NAME_RUNNING)
            decisionTimeSeconds = savedInstanceState.getInt(DECISION_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_random_person, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_NAME_RUNNING, isNameRunning)
        outState.putBoolean(WAS_NAME_RUNNING, wasNameRunning)
        outState.putString(CURRENT_NAME, currentName)
        outState.putInt(DECISION_TIME, decisionTimeSeconds)
    }

    override fun onPause() {
        super.onPause()
        wasNameRunning = isNameRunning
        isNameRunning = false
    }

    override fun onStart() {
        super.onStart()
        updateRunningControllers()

        if (!wasNameRunning) {
            updateNamesViews(currentName)
            updateDecisionTimeTextView(decisionTimeSeconds)
        }

        mainHandler.post(updateTextTask)

        setupButton()
        setupStartButton()
        setupResetButton()
    }

    override fun onResume() {
        super.onResume()
        updateRunningControllers()
    }

    //PRIVATE
    private fun updateRunningControllers() {
        if (wasNameRunning) {
            isNameRunning = true
        }
    }

    private fun updateCurrentNameTextView(name: String) {
        val currentNameTextView = view?.findViewById<TextView>(R.id.currentName)

        if (REPLACE_WITH_STARS) {
            val stars = "*".repeat(name.length)
            currentNameTextView?.text = stars
        } else {
            currentNameTextView?.text = name
        }
    }

    private fun updateDecisionTimeTextView(time: Int) {
        val textView = view?.findViewById<TextView>(R.id.time)
        textView?.text = "$time"
    }

    private fun updateSelectedNameTextView(name: String) {
        val textView = view?.findViewById<TextView>(R.id.selectedName)
        textView?.text = name
    }

    private fun updateNamesViews(name: String) {
        updateCurrentNameTextView(name)
        updateSelectedNameTextView(name)
    }

    private fun updateTextViewsIfRunning() {
        if (isNameRunning) {
            decisionTimeSeconds++
            currentName = getPerson()

            updateCurrentNameTextView(currentName)
            updateDecisionTimeTextView(decisionTimeSeconds)
        }
    }

    private val updateTextTask = object : Runnable {
        override fun run() {
            updateTextViewsIfRunning()
            mainHandler.postDelayed(this, DELAY_MS)
        }
    }

    private fun setupButton() {
        val selectButton = view?.findViewById<Button>(R.id.selectButton)
        selectButton?.setOnClickListener {
            mainHandler.removeCallbacks(updateTextTask)
            updateSelectedNameTextView(currentName)
            isNameRunning = false
        }
    }

    private fun setupResetButton() {
        val selectButton = view?.findViewById<Button>(R.id.resetButton)
        selectButton?.setOnClickListener {
            updateSelectedNameTextView("")
            updateCurrentNameTextView("")
            isNameRunning = false
            currentName = ""
        }
    }

    private fun setupStartButton() {
        val selectButton = view?.findViewById<Button>(R.id.startButton)
        selectButton?.setOnClickListener {
            isNameRunning = true
        }
    }
}