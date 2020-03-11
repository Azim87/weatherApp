package com.example.wheatherapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.model.city.CityModel

class SearchAdapter(private val function: (CityModel) -> Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var cityList: ArrayList<CityModel>? = null

    fun setList(list: ArrayList<CityModel>) {
        cityList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View = LayoutInflater.from(parent.context!!)
            .inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view, function)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(cityList!![position])
    }

    override fun getItemCount(): Int {
        if (cityList != null) {
            return cityList!!.size
        }
        return 0
    }

    inner class SearchViewHolder(itemView: View, val function: (CityModel) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val cityTitle: TextView = itemView.findViewById(R.id.city_title)
        val citySubTitle: TextView = itemView.findViewById(R.id.city_sub_title)

        fun bind(city: CityModel) {
            cityTitle.text = city.name
            citySubTitle.text = city.capital

            itemView.setOnClickListener {
                function(city)
            }
        }
    }
}