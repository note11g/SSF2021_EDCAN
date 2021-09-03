package com.note11.schoolinfoapp.ui.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.databinding.ActivitySplashBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.welcome.WelcomeActivity
import com.note11.schoolinfoapp.ui.screen.main.MainActivity
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loaded.observe(this, {
            // todo : Q3. it이 true 라면 goToMain 함수를 실행해줍시다.
            //  이때, 차례대로 viewModel.subjectList, viewModel.lunchList 를 인수로 넣어주면 됩니다.
            if(it) goToMain(viewModel.subjectList, viewModel.lunchList)
            // -------------------------------------------------
        })

        // todo : Q1. 초기 데이터를 불러오는 함수를 실행합니다.
        loadData()
        // -------------------------------------------------
    }

    private fun loadData() = lifecycleScope.launch {
        val user = DataUtil(this@SplashActivity).getUserInfoOnce()

        if (user != null) {
            viewModel.getAllData(user)
        } else {
            // TODO: Q2. WelcomeActivity 로 이동하는 함수를 실행해줍시다.
            goToWelcome()
            // -------------------------------------------------
        }
    }

    private fun goToWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    private fun goToMain(subjectList: List<SubjectModel>?, lunchList: List<LunchModel>?) {
        val lunchArrayList = viewModel.lunchListProcessing(lunchList)
        val subjectArrayList = arrayListOf<SubjectModel>()
        subjectList?.let { subjectArrayList.addAll(it) }

        Intent(this, MainActivity::class.java).also {
            it.putParcelableArrayListExtra("lunchList", lunchArrayList)
            it.putParcelableArrayListExtra("subjectList", subjectArrayList)

            lifecycleScope.launch {
                it.putExtra("storedTimeInfo", DataUtil(applicationContext).getTimeInfoOnce())
                delay(800)
                startActivity(it)
                finish()
            }
        }
    }
}