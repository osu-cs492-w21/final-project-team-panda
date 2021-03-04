package com.example.android.lifecycleweather

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lifecycleweather.ForecastAdapter.OnForecastItemClickListener
import com.example.android.lifecycleweather.data.ForecastCity
import com.example.android.lifecycleweather.data.ForecastData
import com.example.android.lifecycleweather.service.WeatherService
import com.example.android.lifecycleweather.utils.SettingsActivity
import com.example.android.lifecycleweather.utils.TemperatureUnits
import com.example.android.lifecycleweather.viewmodel.ForecastViewModel

class MainActivity : AppCompatActivity(), OnForecastItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private var forecastAdapter: ForecastAdapter? = null

    private var forecastCity: ForecastCity? = null

    private var forecastListRV: RecyclerView? = null
    private var loadingIndicatorPB: ProgressBar? = null
    private var errorMessageTV: TextView? = null
    private var errorToast: Toast? = null

    private var forecastViewModel: ForecastViewModel? = null

    private var preferenceManager: SharedPreferences? = null
    private var units: TemperatureUnits = TemperatureUnits.Fahrenheit
    private var location: String = "Corvallis,OR,US"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenceManager = PreferenceManager.getDefaultSharedPreferences(this)
        preferenceManager?.registerOnSharedPreferenceChangeListener(this)

        TemperatureUnits.fromString(
                preferenceManager?.getString(
                        getString(R.string.preference_units_key), null
                ) ?: "NO_KEY"
        )?.let {
            units = it
        }
        preferenceManager?.getString(getString(R.string.preference_location_key), null)?.let {
            location = it
        }

        loadingIndicatorPB = findViewById(R.id.pb_loading_indicator)
        errorMessageTV = findViewById(R.id.tv_error_message)
        forecastListRV = findViewById(R.id.rv_forecast_list)

        errorMessageTV?.text = "Unable to load forecast data."

        forecastAdapter = ForecastAdapter(this)

        forecastListRV?.let { recylerView ->
            recylerView.layoutManager = LinearLayoutManager(this)
            recylerView.setHasFixedSize(true)
            recylerView.adapter = forecastAdapter
        }

        forecastViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(ForecastViewModel::class.java)

        forecastViewModel?.weatherService?.forecastData?.observe(this , { newForecastData ->
            newForecastData?.let {
                forecastAdapter?.updateForecastData(it)
                forecastCity = it.forecastCity
                this.title = it.forecastCity.name
            }
        })

        forecastViewModel?.weatherService?.status?.observe(this , { newStatus ->
            newStatus?.let {
                when (newStatus) {
                    WeatherService.Status.Success -> {
                        errorMessageTV?.visibility = View.INVISIBLE
                        loadingIndicatorPB?.visibility = View.INVISIBLE
                        forecastListRV?.visibility = View.VISIBLE
                    }
                    WeatherService.Status.Loading -> {
                        errorMessageTV?.visibility = View.INVISIBLE
                        loadingIndicatorPB?.visibility = View.VISIBLE
                        forecastListRV?.visibility = View.INVISIBLE
                    }
                    WeatherService.Status.Error -> {
                        errorMessageTV?.visibility = View.VISIBLE
                        loadingIndicatorPB?.visibility = View.INVISIBLE
                        forecastListRV?.visibility = View.INVISIBLE
                    }
                }
            }
        })

        loadForecast()
    }

    override fun onForecastItemClick(forecastData: ForecastData?) {
        val intent = Intent(this, ForecastDetailActivity::class.java)
        intent.putExtra(ForecastDetailActivity.EXTRA_FORECAST_DATA, forecastData)
        intent.putExtra(ForecastDetailActivity.EXTRA_FORECAST_CITY, forecastCity)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_map -> {
                viewForecastCityInMap()
                true
            }
            R.id.action_settings -> {
                openSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This function uses an implicit intent to view the forecast city in a map.
     */
    private fun viewForecastCityInMap() {
        forecastCity?.let { forecastCity ->
            val forecastCityGeoUri = Uri.parse(getString(
                    R.string.geo_uri,
                    forecastCity.latitude,
                    forecastCity.longitude,
                    12
            ))
            val intent = Intent(Intent.ACTION_VIEW, forecastCityGeoUri)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                errorToast?.cancel()
                errorToast = Toast.makeText(this, getString(R.string.action_map_error), Toast.LENGTH_LONG)
                errorToast?.show()
            }
        }
    }

    private fun openSettings() {
        intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private val API_KEY: String = BuildConfig.OPEN_WEATHER_API_KEY
    }

    fun loadForecast() {
        forecastViewModel?.weatherService?.loadForecastData(location, units, API_KEY)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let { key ->
            sharedPreferences?.getString(key, null)?.let { newValue ->
                when (key) {
                    getString(R.string.preference_location_key)-> {
                        location = newValue
                        loadForecast()
                    }
                    getString(R.string.preference_units_key) -> {
                        TemperatureUnits.fromString(newValue)?.let {
                            units = it
                            loadForecast()
                        }
                    }
                    else -> {
                        Log.w(TAG, "Unrecognized key '$key'")
                    }
                }
            }
            Log.w(TAG, "Unable to find new value for shared preference '$key'")
        }
        Log.w(TAG, "Shared preference changed null key")
    }

}
