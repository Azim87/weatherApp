package com.example.wheatherapp.ui.search

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.model.city.CityModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.logging.Handler

class SearchFragment : BaseFragment() {
    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter


    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setUpViews(view: View) {
        initRecycler()
        getCityData()
    }

    fun initRecycler(){
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
                Log.d("ololo", " text change " + newText)


                searchViewModel.getCity(newText)
                Log.d("ololo", " text change " + newText)

                searchViewModel.cityData.observe(this@SearchFragment, Observer {data ->
                    if (data != null){
                        searchAdapter.setList(data as ArrayList<CityModel>)
                    }
                })
                return true
            }


            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }
}