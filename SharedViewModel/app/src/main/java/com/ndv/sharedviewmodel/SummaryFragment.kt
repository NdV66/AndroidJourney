package com.ndv.sharedviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels

class SummaryFragment : Fragment() {
    lateinit var listener: IStepperFragmentListener
    private val sharedViewModel by activityViewModels<RingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupDetails()
    }

    private fun setupDetails() {
        val name = requireView().findViewById<TextView>(R.id.summaryRingName)
        val metal = requireView().findViewById<TextView>(R.id.summaryRingMetal)

        name.text = getString(R.string.ring_name_summary, sharedViewModel.name.value)
        metal.text = getString(R.string.ring_metal_summary, sharedViewModel.metal.value)
    }
}