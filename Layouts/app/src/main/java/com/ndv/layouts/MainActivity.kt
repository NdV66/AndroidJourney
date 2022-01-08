package com.ndv.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private fun <T> handleButton(
        id: Int,
        getActivity: () -> Class<T>
    ) {
        val button = findViewById<Button>(id)

        button.setOnClickListener {
            val intent = Intent(this, getActivity())
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleButton(R.id.showLinearHorizontal) { LinearHorizontal::class.java }
        handleButton(R.id.showLinearVertical) { LinearVertical::class.java }
        handleButton(R.id.showLinearVerticalWithWeight) { VerticalWithWeight::class.java }
        handleButton(R.id.showLinearVerticalWithGravity) { VerticalWithGravity::class.java }
        handleButton(R.id.showFrameLayout) { FrameLayout::class.java }
        handleButton(R.id.showElements) { GuiElements::class.java }
    }
}