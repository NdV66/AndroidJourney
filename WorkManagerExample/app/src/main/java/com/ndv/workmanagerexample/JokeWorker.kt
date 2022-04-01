package com.ndv.workmanagerexample

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

const val JOKE_KEY = "jokeKey"

class JokeWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        showNotification(inputData.getString(JOKE_KEY)!!)
        return Result.success()
    }

    private fun showNotification(text: String) {
        println(text)
    }
}
