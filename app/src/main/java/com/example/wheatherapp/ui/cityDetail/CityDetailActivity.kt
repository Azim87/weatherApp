package com.example.wheatherapp.ui.cityDetail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader
import com.example.wheatherapp.R

class CityDetailActivity : AppCompatActivity() {
    private  var flagImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)
        flagImageView = findViewById(R.id.city_flags)

        val cityFlag = intent.getStringExtra("city")
        SvgLoader.pluck()
            .with(this)
            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
            .load(cityFlag, flagImageView);
    }
}
