package com.ndv.workmanagerexample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStartJokeWorkerButton()
    }

    private fun setupStartJokeWorkerButton() {
        val button = findViewById<Button>(R.id.startJokeWorkerButton)
        button.setOnClickListener { createJokeWorker() }
    }

    private fun observeWRequestChanges(workRequest: WorkRequest) {
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workRequest.id)
            .observe(this
            ) { workInfo ->
                if (workInfo != null) {
                    println("[WORK STATE]: ${workInfo.state.name}")
                }
            }
    }

    private fun createWorkRequest(constraints: Constraints, data: Data): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<JokeWorker>()
            .setInputData(data)
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS)
            .build()
    }

    private fun createJokeWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false)
            .build()

        val jokeData = workDataOf(
            JOKE_KEY to getString(R.string.notification_description),
            JOKE_TITLE_KEY to getString(R.string.notification_title),
            JOKE_KEY to getString(R.string.joke))

        val workRequest = createWorkRequest(constraints, jokeData)

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        observeWRequestChanges(workRequest)
    }
}