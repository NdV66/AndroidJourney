package com.ndv.dynamicfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class RandomPersonFragment : Fragment() {
    var currentName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_random_person, container, false)
    }

    override fun onStart() {
        super.onStart()

        val selectButton = view?.findViewById<Button>(R.id.selectButton)
        selectButton?.setOnClickListener{
            updateCurrentName()
        }
    }

    private fun updateCurrentName() {
        currentName = getRandomPerson()
        val currentNameTextView = view?.findViewById<TextView>(R.id.selectedName)
        currentNameTextView?.text = currentName
    }

}