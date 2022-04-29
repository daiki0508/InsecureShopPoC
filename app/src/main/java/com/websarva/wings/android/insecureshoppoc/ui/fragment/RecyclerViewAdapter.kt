package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.insecureshoppoc.R

@Suppress("UNCHECKED_CAST")
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
                            // StorageFragmentの起動
                            transaction(activity).replace(R.id.fragment_container, StorageFragment()).commit()
                        }
                        else -> {
                            throw IllegalArgumentException("Out of range of the array.")
                        }
                    }
                }
                "storage" -> {
                    when(position){
                        0, 1 -> {
                            activity.let {
                                InfoDialogFragment(it.getString(
                                    if (position == 0){
                                        R.string.storage_1_title
                                    }else{
                                        R.string.storage_2_title
                                    }
                                ), it.getString(
                                    if (position == 0){
                                        R.string.storage_1_message
                                    }else{
                                        R.string.storage_2_message
                                    }
                                )).show(it.supportFragmentManager, "InfoDialog")
                            }
                        }
                        else -> {
                            throw IllegalArgumentException("Out of range of the array.")
                        }
                    }
                }
                else ->{
                    throw IllegalArgumentException("Out of range of the array.")
                }
            }
        }
    }

    private fun transaction(activity: FragmentActivity): FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fragment_up_enter, R.anim.fragment_up_exit, android.R.anim.fade_in, android.R.anim.fade_out).addToBackStack(null)

    override fun getItemCount(): Int {
        return (items["items"] as List<String>).size
    }
}