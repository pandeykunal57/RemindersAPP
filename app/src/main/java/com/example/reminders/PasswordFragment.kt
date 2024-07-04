package com.example.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.reminders.databinding.DialogEditReminderBinding
import com.example.reminders.databinding.FragmentPasswordsBinding
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PasswordFragment :Fragment(){
    private lateinit var binding: FragmentPasswordsBinding
    private val preferences by lazy{
        requireActivity().getSharedPreferences("passwords",Context.MODE_PRIVATE)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPasswordsBinding.inflate(inflater,container,false)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diplayValues()
        binding.cardViewWifi.setOnClickListener { showEditDialog(PREF_WIFI) }
        binding.cardViewTabpin.setOnClickListener { showEditDialog(PREF_TABLET) }
        binding.cardViewBikelock.setOnClickListener { showEditDialog(PREF_BIKE) }
    }

    private fun showEditDialog(preferenceKey: String) {
        val dialogbinding=DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
        dialogbinding.editTextValue.setText(preferences.getString(preferenceKey,null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update Value")
            .setView(dialogbinding.root)
            .setPositiveButton("SAVE") { _,_ ->
                preferences.edit { putString(preferenceKey,dialogbinding.editTextValue.text?.toString()) }
                diplayValues()
            }
            .setNeutralButton("Cancel") {_,_ ->}.show()

    }

    private fun diplayValues() {
        binding.texttviewWIFIValue.text=preferences.getString(PREF_WIFI,null)
        binding.texttviewTABValue.text=preferences.getString(PREF_TABLET,null)
        binding.texttviewBIKEValue.text=preferences.getString(PREF_BIKE,null)
    }
    companion object{
        const val PREF_WIFI="pref_WIFI"
        const val PREF_TABLET="pref_TAB"
        const val PREF_BIKE="pref_BIKE"
    }
}
