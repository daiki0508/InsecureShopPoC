package com.websarva.wings.android.insecureshoppoc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.websarva.wings.android.insecureshoppoc.R
import com.websarva.wings.android.insecureshoppoc.databinding.ActivityMainBinding
import com.websarva.wings.android.insecureshoppoc.ui.fragment.TopFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // fragmentの起動
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, TopFragment()).commit()
    }
}