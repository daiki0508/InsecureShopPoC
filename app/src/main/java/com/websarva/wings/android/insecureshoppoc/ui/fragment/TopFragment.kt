package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentTopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopFragment: Fragment() {
    private var _binding: FragmentTopBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerViewの第１引数に渡す値の作成
        val items = mapOf(
            "position" to "top",
            "items" to listOf(getString(R.string.mstg_storage))
        )
        // recyclerViewの作成
        binding.rvTop.adapter = RecyclerViewAdapter(requireActivity(), items)
        binding.rvTop.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}