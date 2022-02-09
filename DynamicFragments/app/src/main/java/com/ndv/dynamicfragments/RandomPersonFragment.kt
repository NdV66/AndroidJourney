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


const val DELAY_MS: Long = 200

class RandomPersonFragment : Fragment() {
    lateinit var mainHandler: Handler
    var currentName: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_random_person, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainHandler = Handler(Looper.getMainLooper())
        setupButton()
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

    private fun updateCurrentName() {
        currentName = getRandomPerson()
        val currentNameTextView = view?.findViewById<TextView>(R.id.currentName)
        currentNameTextView?.text = currentName
    }

    private val updateTextTask = object : Runnable {
        override fun run() {
            updateCurrentName()
            mainHandler.postDelayed(this, DELAY_MS)
        }
    }

    private fun setupButton() {
        val selectButton = view?.findViewById<Button>(R.id.selectButton)
        selectButton?.setOnClickListener {
            mainHandler.removeCallbacks(updateTextTask)


        }
    }
}