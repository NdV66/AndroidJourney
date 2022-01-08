package com.ndv.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class GuiElements : AppCompatActivity() {
    private fun showToast(text: String) {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_elements)

        val spinner = findViewById<Spinner>(R.id.spinner)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val text = "I like ${spinner.selectedItem} too!";
                showToast(text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}