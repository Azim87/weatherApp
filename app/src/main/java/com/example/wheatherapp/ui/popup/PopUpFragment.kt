package com.example.wheatherapp.ui.popup

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_popup.*

class PopUpFragment : BaseFragment() {
    private lateinit var anim: Animation

    companion object {
        fun newInstance(): Fragment {
            return PopUpFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_popup
    }

    override fun setUpViews(view: View) {
        anim = AnimationUtils.loadAnimation(context, R.anim.anim_down)
        popup_close.setOnClickListener {
            popup_close.animate()
            view.animation = anim
            popup_container.visibility = View.GONE
        }
    }
}