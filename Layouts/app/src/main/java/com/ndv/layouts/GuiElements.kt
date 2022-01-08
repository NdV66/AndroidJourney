package com.ndv.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.ToggleButton

class GuiElements : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_elements)

        val switch = findViewById<RadioGroup>(R.id.radioGroup)
        switch.checkedRadioButtonId
    }
}