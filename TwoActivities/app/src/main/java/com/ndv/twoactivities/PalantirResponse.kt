package com.ndv.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        val shareButton = findViewById<Button>(R.id.shareButton)

        val textToDisplay =
            if (target == null)
                getString(R.string.no_target_selected)
            else getString(R.string.palantir_response, target)

        messageView.text = textToDisplay

        shareButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textToDisplay)
            }

            val shareIntent = Intent.createChooser(intent, getString(R.string.share_response_dialog_title))
            startActivity(shareIntent)
        }
    }
}
