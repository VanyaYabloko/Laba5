package com.example.lab5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.model.NumberItem
import com.example.lab5.R

class NumbersAdapter(
    private val items: List<NumberItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)

        fun bind(item: NumberItem) {
            numberTextView.text = item.value.toString()
            itemView.setBackgroundColor(item.color)

            itemView.setOnClickListener {
                onItemClick(item.value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}