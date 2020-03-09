package com.example.wheatherapp.ui.search

import android.view.View
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment

class SearchFragment : BaseFragment() {
    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setUpViews(view: View) {

    }
}