package com.example.workmanager_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workmanager_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWorkManager()
    }

    private fun initWorkManager() {
        /** WorkManager item (딜레이주는방법: ".setInitialDelay(0, TimeUnit.MILLISECONDS)" ) */
        val workA = OneTimeWorkRequestBuilder<WorkManagerWorker>().setInputData(workDataOf("data" to "A")).build()
        val workB = OneTimeWorkRequestBuilder<WorkManagerWorker>().setInputData(workDataOf("data" to "B")).build()
        val workC = OneTimeWorkRequestBuilder<WorkManagerWorker>().setInputData(workDataOf("data" to "C")).build()
        val workD = OneTimeWorkRequestBuilder<WorkManagerWorker>().setInputData(workDataOf("data" to "D")).build()
        val workE = OneTimeWorkRequestBuilder<WorkManagerWorker>().setInputData(workDataOf("data" to "E")).build()

        /** WorkManager 생성 */
        val chain1 = WorkManager.getInstance(this).beginWith(workA).then(workB)
        val chain2 = WorkManager.getInstance(this).beginWith(workC).then(workD)

        /** WorkManager 실행 (직렬, 병렬) */
        val chainAll = WorkContinuation.combine(listOf(chain1, chain2)).then(workE)
        chainAll.enqueue()
    }
}