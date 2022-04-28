package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.insecureshoppoc.R

class RecyclerViewAdapter(
    private val activity: FragmentActivity,
    private val items: Map<String, Any>
): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_top, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = (items["items"] as List<String>)[position]

        // recyclerViewのリストタップ時の処理
        holder.view.setOnClickListener {
            when(items["position"] as String){
                "top" ->{
                    when(position){
                        0 -> {
                            transaction(activity).replace(R.id.fragment_container, StorageFragment()).commit()
                        }
                        else -> {
                            throw IllegalArgumentException("Out of range of the array.")
                        }
                    }
                }
                "storage" -> {
                    TODO("未実装")
                }
                else ->{
                    throw IllegalArgumentException("Out of range of the array.")
                }
            }
        }
    }

    private fun transaction(activity: FragmentActivity): FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fragment_up_enter, R.anim.fragment_up_exit)

    override fun getItemCount(): Int {
        return (items["items"] as List<String>).size
    }
}