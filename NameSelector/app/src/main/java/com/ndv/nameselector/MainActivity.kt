package com.ndv.nameselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findNameButton: Button = findViewById(R.id.find_name)
        val displayName: TextView = findViewById(R.id.display_name)
        val categorySpinner: Spinner = findViewById(R.id.spinner)

        findNameButton.setOnClickListener {
            val selectedCategory = categorySpinner.selectedItem as String
            val foundName = selectNameByCategory(selectedCategory)
            displayName.text = foundName
        }
    }
}
