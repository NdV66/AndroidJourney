package com.ndv.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class NameFragment : Fragment() {
    lateinit var listener: IStepperFragmentListener
    private val sharedViewModel by activityViewModels<RingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupNextButton()
    }

    private fun setupNextButton() {
        val button = requireView().findViewById<Button>(R.id.nameNextButton)

        button.setOnClickListener {
            val ringNameEditText = requireView().findViewById<EditText>(R.id.ringName)
            sharedViewModel.setName(ringNameEditText.text.toString())
            listener.onClick()
        }
    }
}