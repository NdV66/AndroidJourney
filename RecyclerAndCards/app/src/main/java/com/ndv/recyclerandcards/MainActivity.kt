package com.ndv.recyclerandcards

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val MAX_IN_ROW = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(this, MAX_IN_ROW)
        recyclerView.adapter = PersonRecyclerAdapter(VALAR, prepareListener())
    }

    private fun prepareListener(): IPersonRecyclerAdapterListener {
        val currentContext = this;

        return object : IPersonRecyclerAdapterListener {
            override fun onClick(position: Int) {
                val intent = Intent(currentContext, PersonDetailsActivity::class.java)
                intent.putExtra(PERSON_NAME, VALAR[position].name)
                currentContext.startActivity(intent)
            }
        }
    }
}