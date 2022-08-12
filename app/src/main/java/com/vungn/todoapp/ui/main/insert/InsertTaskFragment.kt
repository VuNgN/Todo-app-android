package com.vungn.todoapp.ui.main.insert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentInsertTaskBinding

class InsertTaskFragment : Fragment() {
    private lateinit var binding: FragmentInsertTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInsertTaskBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        handleEvent()
    }

    private fun handleEvent() {
        binding.apply {
            dueDate.setEndIconOnClickListener {
                calendarPicker.visibility = if (calendarPicker.visibility == View.GONE)
                    View.VISIBLE else View.GONE
            }

            dueDateEditText.setOnClickListener {
                calendarPicker.visibility = if (calendarPicker.visibility == View.GONE)
                    View.VISIBLE else View.GONE
            }

            calendarPicker.setOnDateChangeListener { _, year, month, dayOfMonth ->
                dueDateEditText.setText(
                    resources.getString(
                        R.string.due_date,
                        dayOfMonth.toString(),
                        (month + 1).toString(),
                        year.toString()
                    )
                )
            }

            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            dueDate.setOnFocusChangeListener { v, hasFocus ->
                Log.d("aaaaaa", "handleEvent: " + hasFocus)
            }
        }
    }

    private fun setupUi() {
        val timeTypes = listOf("Yearly", "Monthly", "Weekly", "Daily", "Never")
        val dropdownAdapter =
            ArrayAdapter(requireContext(), R.layout.item_dropdown_insert_task, timeTypes)
        binding.apply {
            calendarPicker.visibility = View.GONE
            (remindMe.editText as AutoCompleteTextView).setAdapter(dropdownAdapter)
            (remindMe.editText as AutoCompleteTextView).setText(dropdownAdapter.getItem(3), false)

            (repeat.editText as AutoCompleteTextView).setAdapter(dropdownAdapter)
            (repeat.editText as AutoCompleteTextView).setText(dropdownAdapter.getItem(3), false)
        }
    }

}