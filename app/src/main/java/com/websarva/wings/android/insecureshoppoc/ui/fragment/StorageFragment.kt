package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentStorageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment: Fragment() {
    private var _binding: FragmentStorageBinding? = null
    private val binding: FragmentStorageBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerViewの第１引数に渡す値の作成
        val items = mapOf(
            "position" to "storage",
            "items" to listOf(getString(R.string.storage_1))
        )
        // recyclerViewの作成
        binding.rvStorage.adapter = RecyclerViewAdapter(requireActivity(), items)
        binding.rvStorage.layoutManager = LinearLayoutManager(activity)
    }
}