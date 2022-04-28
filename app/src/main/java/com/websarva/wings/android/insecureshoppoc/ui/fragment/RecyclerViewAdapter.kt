package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.insecureshoppoc.R

class RecyclerViewAdapter(
    private val items: Map<String, Any>
): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_top, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = (items["items"] as List<String>)[position]
    }

    override fun getItemCount(): Int {
        return (items["items"] as List<String>).size
    }
}