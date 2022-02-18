package com.ndv.recyclerandcards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val MAX_IN_ROW = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this, MAX_IN_ROW)
        recyclerView.adapter = PersonRecyclerAdapter(VALAR)
    }
}