package com.ndv.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PalantirResponse : AppCompatActivity() {
    companion object {
        const val REQUEST_TARGET = "requestTarget"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palantir_response)

        val messageView = findViewById<TextView>(R.id.palantirResponse)
        val target = intent.getStringExtra(REQUEST_TARGET)

        messageView.text =
            if (target == null)
                getString(R.string.no_target_selected)
            else getString(R.string.palantir_response, target)
    }
}
