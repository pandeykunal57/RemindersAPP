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
import com.example.reminders.databinding.FragmentsGeneralInfoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralInfoFragment:Fragment() {
    private lateinit var binding: FragmentsGeneralInfoBinding
    private val preferences by lazy{
        requireActivity().getSharedPreferences("details", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentsGeneralInfoBinding.inflate(inflater, container ,false)
        return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diplayValues()
        binding.cardViewInsurance.setOnClickListener { showEditDialog(PREF_INSURANCE) }
        binding.cardViewWATER.setOnClickListener { showEditDialog(PREF_WATER) }
        binding.cardViewPAN.setOnClickListener { showEditDialog(PREF_PAN) }
    }

    private fun showEditDialog(preferenceKey: String) {
        val dialogbinding= DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
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
        binding.texttviewINSURANCEValue.text=preferences.getString(PREF_INSURANCE,null)
        binding.texttviewWATERValue.text=preferences.getString(PREF_WATER,null)
        binding.texttviewPANValue.text=preferences.getString(PREF_PAN,null)
    }
    companion object{
        const val PREF_INSURANCE="pref_INSURANCE"
        const val PREF_WATER="pref_WATER"
        const val PREF_PAN="pref_PAN"
    }
}