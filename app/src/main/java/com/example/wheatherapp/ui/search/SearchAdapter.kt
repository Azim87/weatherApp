package com.example.wheatherapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.model.city.CityModel

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private lateinit var cityList: ArrayList<CityModel>

    fun setList(list: ArrayList<CityModel>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View = LayoutInflater.from(parent.context!!).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
       return 0
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityTitle: TextView = itemView.findViewById(R.id.city_title)
        val citySubTitle: TextView = itemView.findViewById(R.id.city_sub_title)

        fun bind() {

        }
    }
}