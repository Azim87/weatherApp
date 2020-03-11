package com.example.wheatherapp.ui.search

import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.model.city.CityModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchProgressBar: ProgressBar

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setUpViews(view: View) {
        searchProgressBar = view.findViewById(R.id.search_progress)
        initRecycler()
        getCityData()

    }

    private fun initRecycler() {
        search_recycler.apply {
            searchAdapter = SearchAdapter()
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getCityData() {

        search_view.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                searchProgressBar.visibility = View.VISIBLE
                val timer = object : CountDownTimer(2000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        searchViewModel.getCity(newText)
                        searchViewModel.cityData.observe(this@SearchFragment,
                            Observer { data ->
                                if (data != null) {
                                    searchAdapter.setList(data as ArrayList<CityModel>)
                                }
                            })
                        searchProgressBar.visibility = View.INVISIBLE
                    }
                }
                timer.start()
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

    private fun loadCities() {
        searchViewModel.loading.observe(this@SearchFragment, Observer { isLoading ->
            if (isLoading != null) {
                when (isLoading) {
                    true -> searchProgressBar.visibility = View.VISIBLE
                    false -> searchProgressBar.visibility = View.INVISIBLE
                }
            }
        })
    }
}