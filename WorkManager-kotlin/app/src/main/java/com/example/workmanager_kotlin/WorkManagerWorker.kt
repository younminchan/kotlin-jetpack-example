package com.example.workmanager_kotlin

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkManagerWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val w_data = inputData.getString("data").toString()
        Log.e("WorkManager", "w_data: $w_data")

        return Result.success()
    }
}