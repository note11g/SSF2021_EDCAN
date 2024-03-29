package com.note11.schoolinfoapp.ui.screen.first.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.databinding.ActivitySearchBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.select.SelectActivity
import kotlinx.coroutines.flow.*


class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        //todo : Q5. RecyclerView 를 초기 설정하는 코드를 작성해주세요.

        // -------------------------------------------------
        viewModel.searchQuery.observe(this, {
            val length = it.length
            //todo : Q6. 입력한 학교 이름의 길이(length)가 1 보다 길때 학교를 검색해줍시다.
            // 이때, 학교를 검색하는 함수는 viewModel.search() 입니다.

            // -------------------------------------------------
        })
    }

    private fun initRecyclerView() = with(binding) {
        val adapter = SearchAdapter {
            //todo: Q7. 여기는 학교를 선택했을 때(학교 버튼을 눌렀을 때) 실행되는 부분입니다.
            // it 이라는 변수를 SelectActivity 에 전달하고, 화면을 넘어가려 합니다.
            // 어떤 함수를 실행해야 할까요?

            // -------------------------------------------------
        }
        rcvSearchList.layoutManager = LinearLayoutManager(this@SearchActivity)
        rcvSearchList.adapter = adapter
    }

    private fun goToSelect(info: SchoolModel) {
        val intent = Intent(this, SelectActivity::class.java)
        intent.putExtra("schoolInfo", info)
        startActivity(intent)
    }
}
