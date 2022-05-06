package com.websarva.wings.android.insecureshoppoc.ui.fragment.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentStorage3Binding
import com.websarva.wings.android.insecureshoppoc.viewmodel.fragment.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Storage3Fragment: Fragment() {
    private var _binding: FragmentStorage3Binding? = null
    private val binding
    get() = _binding!!

    private val viewModel: StorageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorage3Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // btStartボタンをタップ時の処理
        binding.btStart.setOnClickListener {
            Toast.makeText(activity, getString(R.string.storage_3_toast_start), Toast.LENGTH_SHORT).show()
            viewModel.startLog()
        }

        // btFinishボタンをタップ時の処理
        binding.btFinish.setOnClickListener {
            Toast.makeText(activity, getString(R.string.storage_3_toast_finish), Toast.LENGTH_SHORT).show()
            viewModel.finishLog()
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}