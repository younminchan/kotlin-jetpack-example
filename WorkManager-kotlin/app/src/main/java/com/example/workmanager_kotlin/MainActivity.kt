package com.example.workmanager_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.workmanager_kotlin.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWorkManager()
    }

    private fun initWorkManager() {
        /** WorkManager Delay(딜레이) 설정 */
        val work_delay = OneTimeWorkRequestBuilder<WorkManagerWorker>()
            .setInitialDelay(10, TimeUnit.MINUTES)
            .build()

        /** WorkManager constraint(제약조건) 설정 */
        val w_constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)    //네트워크가 연결된 경우
            .setRequiredNetworkType(NetworkType.UNMETERED)    //무제한 네트워크에 연결된 경우(ex. WIFI 등)
            .setRequiredNetworkType(NetworkType.METERED)      //데이터 통신망에 연결된 경우
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED) //네트워크가 필요 없는 경우
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)  //비 로밍 네트워크가 연결된 경우

            .setRequiresBatteryNotLow(true) //기기의 배터리 부족모드인 경우 제업 제한여부 설정
            .setRequiresCharging(true)      //기기가 충전중인 경우에만 작업 실행
            .setRequiresDeviceIdle(true)    //작업이 실행되기전 사용자 기기가 휴혹 상태여야함. 사용자 기기에서 활발하게 실행되는
                                            //다른앱의 성능에 부정적인 영향을 줄 수 있는 배치작업 실행하는데 유용
            .setRequiresStorageNotLow(true) //사용자 기기 저장공이 부족한 경우 작업 제한여부 설정
            .build()

        val work_constraint = OneTimeWorkRequestBuilder<WorkManagerWorker>()
            .setConstraints(w_constraint) //제약설정
            .build()

        /** WorkManager item */
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