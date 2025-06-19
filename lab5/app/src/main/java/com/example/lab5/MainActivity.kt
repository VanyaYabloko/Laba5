package com.example.lab5

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.adapter.NumbersAdapter
import com.example.lab5.model.NumberItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val items = List(24) {
            NumberItem(
                value = (1..99).random(),
                color = Color.rgb(
                    (0..255).random(),
                    (0..255).random(),
                    (0..255).random()
                )
            )
        }

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = NumbersAdapter(items) { number ->
            showNumberDialog(number)
        }
    }

    private fun showNumberDialog(number: Int) {
        AlertDialog.Builder(this)
            .setTitle("Число")
            .setMessage("Вы выбрали число: $number")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}