package com.example.wheatherapp.ui.map

import android.view.View
import androidx.fragment.app.Fragment
import com.example.wheatherapp.R
import com.example.wheatherapp.base.BaseFragment
import com.example.wheatherapp.ui.popup.PopUpFragment
import com.example.wheatherapp.utils.Show
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map.*
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
                139.01,
                35.02,
                "metric"
            )
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