package com.example.lab52

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                Log.d("BootCompletedReceiver", "Пристрій завантажено")
                Toast.makeText(
                    context,
                    "Пристрій успішно завантажено!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}