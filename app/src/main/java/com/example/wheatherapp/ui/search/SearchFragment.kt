package com.example.wheatherapp.ui.search

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
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

        search_view.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == null || newText.isEmpty()) {
                    Log.d("ololo", "not found")
                } else {
                    searchViewModel.getCity(newText)
                    Log.d("ololo", " search " + newText)
                }
                return true
            }
        })
    }
}