package com.websarva.wings.android.insecureshoppoc.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoDialogFragment(
    private val title: String,
    private val message: String
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK"){_, _ ->

                }
            builder.create()
        }

        return dialog ?: throw IllegalStateException("activity is null.")
    }
}