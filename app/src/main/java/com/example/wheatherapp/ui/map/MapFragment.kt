package com.example.wheatherapp.ui.map

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.ui.popup.PopUpFragment
import com.example.wheatherapp.utils.NetworkHelper
import com.example.wheatherapp.utils.Show
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_popup.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener {

    companion object {
        private const val REQUEST_CODE = 101
        fun newInstance(): Fragment {
            return MapFragment()
        }
    }

    private val mapViewModel by viewModel<MapViewModel>()
    private lateinit var mMap: GoogleMap
    private var name: String? = null

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

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permisson granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "This app requires location permissions to be granted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun addMarkerOnMapClick(location: LatLng) {
        mMap.clear()
        mMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .title("My marker")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .draggable(true)
            )
            .showInfoWindow()
    }

    private fun initPopUpFragment() {
        val mapFragment = SupportMapFragment()
        fragmentManager?.beginTransaction()
            ?.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
            ?.detach(this)
            ?.attach(this)
            ?.add(R.id.globe_container, PopUpFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun animateCamera(location: LatLng) {
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude), 4f
            )
        )
    }

    @SuppressLint("SetTextI18n")
    private fun initViewModel(location: LatLng) {
        mMap.setOnInfoWindowClickListener {
            initPopUpFragment()

            mapViewModel.loadWeatherData(
                location.longitude,
                location.latitude,
                "Metric"
            )

            mapViewModel.weatherList.observe(this, Observer { weather ->
                val iconCode = weather.weather[0].icon;
                val iconUrl = "http://openweathermap.org/img/w/$iconCode.png";
                if (weather != null) {
                    Picasso
                        .get()
                        .load(iconUrl)
                        .resize(150, 150)
                        .centerCrop()
                        .into(temp_icon)
                    if (city_title.text == null) city_title.text = "нет такого места"
                    else city_title.text = weather.name

                    temperature.text = weather.main.temp.toInt().toString() + " °C"
                    temp_description.text = weather.weather.get(0).description
                    temp_humidity.text = weather.main.humidity.toString() + "% влажности"
                }
            })

            mapViewModel.showLoading.observe(this, Observer { showLoading ->

            })

            mapViewModel.showError.observe(this, Observer {showError ->
                Show.message(context!!, showError)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener { location ->
            if(!NetworkHelper().isOnline()) {
                Show.message(context!!, "check internet connection")
            } else {
                getLocationPermission()
                addMarkerOnMapClick(location)
                animateCamera(location)
                initViewModel(location)
            }
        }
    }

    override fun onCameraMove() {
    }
}