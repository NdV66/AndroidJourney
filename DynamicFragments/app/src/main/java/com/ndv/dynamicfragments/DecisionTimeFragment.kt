package com.ndv.dynamicfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DecisionTimeFragment : Fragment() {
    var decisionTime = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_decision_time, container, false)
    }

    override fun onStart() {
        super.onStart()
        val decisionTimeTextView = view?.findViewById<TextView>(R.id.time)
        decisionTimeTextView?.text = "$decisionTime"
    }
}