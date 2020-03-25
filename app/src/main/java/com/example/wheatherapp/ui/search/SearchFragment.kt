package com.example.wheatherapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.model.city.CityModel
import com.example.wheatherapp.ui.cityDetail.CityDetailActivity
import com.example.wheatherapp.utils.Show
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchProgressBar: LottieAnimationView

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            searchAdapter = SearchAdapter(this@SearchFragment::onClickItem)
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onClickItem(city: CityModel) {
        val intent = Intent(context, CityDetailActivity::class.java)
        intent.putExtra("city", city.flag)
        startActivity(intent)
        Show.message(context!!, "" + city.name)
    }

    private fun getCityData() {
        search_view.queryHint = "Введите название города"
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(cityQuery: String): Boolean {
                initViewModel(cityQuery)
                return true
            }
        })
    }

    private fun initViewModel(cityQuery: String) {
        searchViewModel.cityList.observe(this@SearchFragment, Observer { newCityList ->
            searchAdapter.setList(newCityList as ArrayList<CityModel>)
        })

        searchViewModel.showLoading.observe(this, Observer { showLoading ->
            searchProgressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })

        searchViewModel.showError.observe(this, Observer { showError ->
            Show.message(context!!, showError)
        })
        searchViewModel.loadCities(cityQuery)
    }
}