package com.ndv.designlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ValarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_valar, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupList()
    }

    private fun setupList() {
        val view = requireView()
        val list = view.findViewById<ListView>(R.id.valarList)

        list.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, VALAR)
    }
}