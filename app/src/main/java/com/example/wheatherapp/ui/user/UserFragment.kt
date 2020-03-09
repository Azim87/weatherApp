package com.example.wheatherapp.ui.user

import android.view.View
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment

class UserFragment : BaseFragment() {
    companion object {
        fun newInstance() :Fragment {
            return UserFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun setUpViews(view: View) {
    }
}