package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CodeFragment: Fragment() {
    private var _binding: FragmentCodeBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerViewの第１引数に渡す値の作成
        val items = mapOf(
            "position" to "code",
            "items" to listOf(
                getString(R.string.mstg_code_1),
                getString(R.string.mstg_code_2),
                getString(R.string.mstg_code_4)
            )
        )
        // recyclerViewの作成
        binding.rvCode.adapter = RecyclerViewAdapter(requireActivity(), items)
        binding.rvCode.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}