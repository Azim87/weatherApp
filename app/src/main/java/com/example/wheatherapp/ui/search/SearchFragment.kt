package com.example.wheatherapp.ui.search

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    private val searchViewModel by viewModel<SearchViewModel>()


    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setUpViews(view: View) {
        getCityData()
    }

    private fun getCityData() {
        searchViewModel.cityData.observe(this, Observer { searchData ->
            Log.d("ololo", "search fragment ${searchData}")
        })

        searchViewModel.getCity("tallinn")
    }
}