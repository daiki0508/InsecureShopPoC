package com.websarva.wings.android.insecureshoppoc.ui.fragment.platform

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentPlatform3Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Platform3Fragment: Fragment() {
    private var _binding: FragmentPlatform3Binding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatform3Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // defaultのラジオボタン
        binding.rb1.isChecked = true

        // cb1変更時のListener
        binding.cb1.setOnCheckedChangeListener { _, isChecked ->
            binding.button.text = getString(if (isChecked){
                R.string.mstg_platform_3_bt_display
            }else{
                R.string.mstg_platform_3_bt_attack
            })
        }

        // buttonタップ時の処理
        binding.button.setOnClickListener {
            if (binding.cb1.isChecked){
                activity?.let {
                    InfoDialogFragment(getString(R.string.mstg_platform_3_title), getString(R.string.mstg_platform_3_message)).show(it.supportFragmentManager, "InfoDialog")
                }
            }else{
                // intentの定義
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("insecureshop://com.insecureshop/web?url=http://example.com")
                    setClassName("com.insecureshop", "com.insecureshop.WebViewActivity")
                }
                // 対象アクティビティの起動
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}