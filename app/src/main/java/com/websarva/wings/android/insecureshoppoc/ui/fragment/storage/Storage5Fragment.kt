package com.websarva.wings.android.insecureshoppoc.ui.fragment.storage

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentStorage5Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Storage5Fragment: Fragment() {
    private var _binding: FragmentStorage5Binding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorage5Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // btDisplayボタンをタップ時の処理
        binding.btDisplay.setOnClickListener {
            activity?.let {
                val manager = it.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                try {
                    val clipBoardData = manager.primaryClip!!.getItemAt(0).text.toString()
                    InfoDialogFragment(getString(R.string.storage_5_title), clipBoardData).show(it.supportFragmentManager, "InfoDialog")
                }catch (e: NullPointerException){
                    Toast.makeText(activity, getString(R.string.storage_5_toast_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}