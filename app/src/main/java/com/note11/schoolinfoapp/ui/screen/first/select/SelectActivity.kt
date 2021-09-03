package com.note11.schoolinfoapp.ui.screen.first.select

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.ClassModel
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.databinding.ActivitySelectBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.time.SetTimeActivity
import com.note11.schoolinfoapp.util.HintSpinnerAdapter

class SelectActivity : BaseActivity<ActivitySelectBinding>(R.layout.activity_select) {
    override val viewModel: SelectViewModel by viewModels()
    private lateinit var receivedInfo: SchoolModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity() = let { act ->
        receivedInfo = intent.getParcelableExtra("schoolInfo")!!
        viewModel.getClassList(receivedInfo)

        val gradeAdapter = HintSpinnerAdapter(act, "학년")
        val classAdapter = HintSpinnerAdapter(act, "반")

        binding.run {
            spnSelectGrade.adapter = gradeAdapter
            spnSelectClass.adapter = classAdapter

            btnSelectNext.setOnClickListener {
                // todo : Q8. 모두 입력되었는지 확인하는 함수를 실행합니다.

                // -------------------------------------------------
            }
        }

        viewModel.run {
            gradeList.observe(act, { gradeAdapter.setList(it) })
            classList.observe(act, { classAdapter.setList(it) })
        }
    }

    private fun checkSelect() {
        val grade = (binding.spnSelectGrade.selectedItem as String).replace("학년", "")
        val classNum = (binding.spnSelectClass.selectedItem as String).replace("반", "")

        if (grade.isEmpty() || classNum.isEmpty()) {
            //todo : Q9. 입력을 하라는 토스트 메시지를 띄워줍니다.

            // -------------------------------------------------
        } else {
            val userInfo = UserModel(receivedInfo, ClassModel(grade, classNum))

            //todo : Q10. 유저 정보(userInfo)를 넣어 SetTimeActivity 로 이동해줍니다.
            // 무슨 함수를 실행해야 할까요?

            // -------------------------------------------------
        }
    }

    private fun goToSetTime(info : UserModel) {
        val intent = Intent(this, SetTimeActivity::class.java).putExtra("userInfo", info)
        startActivity(intent)
    }
}