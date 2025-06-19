package com.example.lab52

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var airplaneModeTextView: TextView
    private val powerConnectionReceiver = PowerConnectionReceiver()
    private val airplaneModeReceiver = AirplaneModeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        airplaneModeTextView = findViewById(R.id.airplaneModeTextView)
    }

    override fun onStart() {
        super.onStart()

        // Реєстрація динамічних приймачів
        registerReceiver(
            powerConnectionReceiver,
            IntentFilter().apply {
                addAction(Intent.ACTION_POWER_CONNECTED)
                addAction(Intent.ACTION_POWER_DISCONNECTED)
            }
        )

        registerReceiver(
            airplaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(powerConnectionReceiver)
        unregisterReceiver(airplaneModeReceiver)
    }

    // Внутрішній клас для обробки підключення живлення
    inner class PowerConnectionReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_POWER_CONNECTED -> {
                    Toast.makeText(
                        context,
                        "Зарядний пристрій підключено",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    Toast.makeText(
                        context,
                        "Зарядний пристрій відключено",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // Внутрішній клас для обробки зміни режиму польоту
    inner class AirplaneModeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            airplaneModeTextView.text = if (isAirplaneModeOn) {
                "Режим польоту: УВІМКНЕНО"
            } else {
                "Режим польоту: ВИМКНЕНО"
            }
        }
    }
}