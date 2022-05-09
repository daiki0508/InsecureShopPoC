package com.websarva.wings.android.insecureshoppoc.ui.fragment.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentCode2Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Code2Fragment: Fragment() {
    private var _binding: FragmentCode2Binding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCode2Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // defaultのラジオボタン
        binding.rb1.isChecked = true

        // btDisplayタップ時の処理
        binding.btDisplay.setOnClickListener {
            activity?.let {
                InfoDialogFragment(getString(R.string.mstg_code_2_title), getString(
                    when(binding.radioGroup.checkedRadioButtonId){
                        binding.rb1.id -> {
                            R.string.mstg_code_2_1_message
                        }
                        binding.rb2.id -> {
                            R.string.mstg_code_2_2_message
                        }
                        else -> {
                            throw IllegalArgumentException("Out of range of the array.")
                        }
                    }
                )).show(it.supportFragmentManager, "InfoDialog")
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}