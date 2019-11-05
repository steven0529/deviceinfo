package com.steven.deviceinfo

import android.content.res.Configuration
import android.content.res.Configuration.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_brand.text = "BRAND: ${Build.BRAND}"
        tv_model.text = "MODEL: ${Build.MODEL}"
        tv_manufacturer.text = "MANUFACTURER: ${Build.MANUFACTURER}"
        tv_os_ver.text = "OS VERSION: ${Build.VERSION.RELEASE}"

        val metrics = resources.displayMetrics

        tv_dpi.text = "DPI: ${(metrics.density * 160f).toInt().toString()}"

        val density = resources.displayMetrics.density

        if (density == 0.75f) {
            // LDPI
            tv_density.text = "DENSITY: LDPI"
        } else if (density >= 1.0f && density < 1.5f) {
            // MDPI
            tv_density.text = "DENSITY: MDPI"
        } else if (density == 1.5f) {
            // HDPI
            tv_density.text = "DENSITY: HDPI"
        } else if (density > 1.5f && density <= 2.0f) {
            // XHDPI
            tv_density.text = "DENSITY: XHDPI"
        } else if (density > 2.0f && density <= 3.0f) {
            // XXHDPI
            tv_density.text = "DENSITY: XXHDPI"
        } else {
            // XXXHDPI
            tv_density.text = "DENSITY: XXXHDPI"
        }

        tv_screen_size.text = "SIZE (WIDTH X HEIGHT): ${metrics.widthPixels} X ${metrics.heightPixels}"

        val x = (metrics.widthPixels / metrics.xdpi).toDouble().pow(2.0)
        val y = (metrics.heightPixels / metrics.ydpi).toDouble().pow(2.0)

        val decimalFormat = DecimalFormat("#.##")

        tv_screen_physical_size.text = "PHYSICAL SIZE: ${decimalFormat .format(sqrt(x + y))} inches"


        var category = "NO CATEGGORY ASSOCIATED"

        val screenSize =
            resources.configuration.screenLayout and SCREENLAYOUT_SIZE_MASK


        when (screenSize) {
            SCREENLAYOUT_SIZE_LARGE -> category = "LARGE"
            SCREENLAYOUT_SIZE_NORMAL -> category = "NORMAL"
            SCREENLAYOUT_SIZE_SMALL -> category = "SMALL"
            SCREENLAYOUT_SIZE_XLARGE -> category = "XL"
            SCREENLAYOUT_SIZE_MASK -> category = "MASK"
            SCREENLAYOUT_SIZE_UNDEFINED -> category = "UNDEF/TOO LARGE"
        }

        tv_size_category.text = "SIZE CATEGORY: (" + screenSize + ") " + category
    }

}
