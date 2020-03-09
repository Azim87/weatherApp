package com.example.wheatherapp.ui.map

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.ui.popup.PopUpFragment
import com.example.wheatherapp.utils.Show
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_popup.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener {

    private lateinit var mMap: GoogleMap
    private val mapViewModel by viewModel<MapViewModel>()

    companion object {
        fun newInstance(): Fragment {
            return MapFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_map
    }

    override fun setUpViews(view: View) {
        val mapFragment = SupportMapFragment()
        fragmentManager?.beginTransaction()?.add(R.id.google_map, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
        globe_fab.setOnClickListener {
            Show.message(context!!, "click")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener {
            val mapFragment = SupportMapFragment()
            fragmentManager?.beginTransaction()
                ?.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                ?.add(R.id.globe_container, PopUpFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()

            mapViewModel.getRemoteWeatherData(
                74.5900000,
                42.8700000,
                "Metric"
            )

            mapViewModel.weatherData.observe(this, Observer { weather ->
                val iconcode = weather.weather[0].icon;
                val iconurl = "http://openweathermap.org/img/w/" + iconcode + ".png";

                Glide.with(temp_icon.context).load(iconurl).into(temp_icon)

                if(city_title == null) city_title.text = "нет такого места"
                else city_title.text = weather.name

                temperature.text = weather.main.temp.toInt().toString() + " °C"
                temp_description.text = weather.weather.get(0).description
                temp_humidity.text = weather.main.humidity.toString() + "% влажности"
            })

        }
    }

    override fun onCameraMove() {
    }

    fun onBackPress() {
        fragmentManager?.beginTransaction()
            ?.setCustomAnimations(R.anim.anim_up, R.anim.anim_down)
            ?.remove(PopUpFragment())
            ?.commit()
    }
}