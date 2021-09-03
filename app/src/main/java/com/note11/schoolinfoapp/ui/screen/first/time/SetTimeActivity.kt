package com.note11.schoolinfoapp.ui.screen.first.time

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.TimeModel
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.databinding.ActivitySetTimeBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.main.MainActivity
import com.note11.schoolinfoapp.ui.screen.splash.SplashActivity
import com.note11.schoolinfoapp.util.DataUtil
import com.note11.schoolinfoapp.util.alarm.NotificationUtil
import kotlinx.coroutines.launch

class SetTimeActivity : BaseActivity<ActivitySetTimeBinding>(R.layout.activity_set_time) {
    override val viewModel: SetTimeViewModel by viewModels()
    private lateinit var receivedInfo: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun getUserInfo() {
        receivedInfo = intent.getParcelableExtra("userInfo")!!
    }

    private fun initActivity() {
        // todo : Q11. 전 액티비티에서 가져온 데이터를 이용해 유저 데이터를 여기서 불러오려 합니다.
        //  어떤 함수를 실행해야 할까요?

        // --------------------------------------

        binding.vm = viewModel

        viewModel.classBeforeLunch.observe(this, {
            if (it.toIntOrNull() != null) {
                viewModel.lunchEndPeriod.value = "${it.toInt() + 1}교시는 언제 시작하나요?"
            }
        })

        binding.btnTimeNext.setOnClickListener {
            //todo : Q12. id가 btn_time_next 인 버튼을 눌렀을 때 이 안의 코드가 실행됩니다.
            //  이 안에서는 데이터가 모두 입력되었는지 확인(검증)하고, 데이터를 저장하는 코드가 들어와야 합니다.
            //  즉, 초기 세팅(SetUp)이 끝난 상태이지요. 어떤 함수를 실행해야 할까요?

            // --------------------------------------
        }
    }

    private fun endToSetUp() = let { act ->
        // todo : Q13. EditText 에 입력한 값들을 가져와야 합니다.
        //  TODO("어떤 함수를 실행해야 할까요?") 함수를 지우고 그곳에 넣어주세요.
        val inputTime = TODO("어떤 함수를 실행해야 할까요?")
        // --------------------------------------

        if (inputTime == null) {
            // todo : Q14. 시간을 모두 입력하지 않았을 때, 토스트 메시지를 띄워주려 합니다. 어떤 코드가 들어가야할까요?

            // --------------------------------------
        } else lifecycleScope.launch {
            DataUtil(act).run {
                setUserInfo(receivedInfo)
                setTimeInfo(inputTime)
            }
            NotificationUtil(applicationContext).notificationSetting(7, 45)

            //todo : Q15. SplashActivity 로 이동하려면 어떤 함수를 작성해야 할까요?

            // --------------------------------------
        }
    }

    private fun goToSplash() {
        startActivity(Intent(this, SplashActivity::class.java))
        ActivityCompat.finishAffinity(this)
    }
}