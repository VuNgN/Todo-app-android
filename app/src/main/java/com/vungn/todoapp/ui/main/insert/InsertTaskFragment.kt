package com.vungn.todoapp.ui.main.insert

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentInsertTaskBinding
import com.vungn.todoapp.ui.main.activity.constract.MainViewModel
import com.vungn.todoapp.ui.main.activity.constract.implement.MainViewModelImpl
import com.vungn.todoapp.ui.main.insert.contract.InsertTaskViewModel
import com.vungn.todoapp.ui.main.insert.contract.implement.InsertTaskViewModelImpl
import com.vungn.todoapp.util.TimeUtil.formatToISO8601
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class InsertTaskFragment : Fragment(), LifecycleOwner {
    private lateinit var binding: FragmentInsertTaskBinding
    private val vm: InsertTaskViewModel by viewModels<InsertTaskViewModelImpl>()
    private val viewmodelMainActivity: MainViewModel by activityViewModels<MainViewModelImpl>()

    private val calendarConstraints = CalendarConstraints.Builder()
        .setValidator(DateValidatorPointForward.now())
    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Due date")
        .setCalendarConstraints(calendarConstraints.build())
        .build()
    private val timePicker =
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setTitleText("Due date")
            .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentInsertTaskBinding.inflate(inflater, container, false).also {
        binding = it
        binding.lifecycleOwner = this
        binding.vm = vm
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        handleEvent()
    }

    private fun handleEvent() {
        datePicker.addOnPositiveButtonClickListener {
            if (!timePicker.isAdded) {
                timePicker.show(parentFragmentManager, "")
            }
        }
        timePicker.addOnPositiveButtonClickListener {
            onTimePickSelectItem()
        }
        binding.apply {
            dueDateEditText.setOnClickListener {
                if (!datePicker.isAdded) {
                    datePicker.show(parentFragmentManager, "tag")
                }
            }
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            participantsEditText.setOnClickListener {
                findNavController().navigate(R.id.action_insertTaskFragment_to_addUserInTaskFragment,
                    null)
            }
        }

        viewmodelMainActivity.newLiveDataUserGuest.observe(viewLifecycleOwner) {
            if (it.size < 1) {
                binding.participantsEditText.setText("No participants yet")
            } else if (it.size == 1) {
                binding.participantsEditText.setText("${it.get(0).name} join")
            } else if (it.size > 1) {
                binding.participantsEditText.setText("${it.get(0).name} and ${it.size - 1} other people join")
            }
        }
    }

    private fun onTimePickSelectItem() {
        val date = Calendar.getInstance().apply {
            time = Date(datePicker.selection!!)
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
        }.time
        val formattedDate = formatToISO8601(date)
        Log.d("", "datePicker: $formattedDate")
        vm.dueDate().postValue(formattedDate)
    }

    private fun setupUi() {
        val timeTypes = listOf("Yearly", "Monthly", "Weekly", "Daily", "Never")
        val dropdownAdapter =
            ArrayAdapter(requireContext(), R.layout.item_dropdown_insert_task, timeTypes)
        binding.apply {
            (repeat.editText as AutoCompleteTextView).setAdapter(dropdownAdapter)
            (repeat.editText as AutoCompleteTextView).setText(dropdownAdapter.getItem(3), false)
        }

        vm.isLoading().observe(viewLifecycleOwner) {
            binding.apply {
                if (it) {
                    saveButton.visibility = View.GONE
                    indicator.visibility = View.VISIBLE
                } else {
                    saveButton.visibility = View.VISIBLE
                    indicator.visibility = View.GONE
                    vm?.clearTextField()
                }
            }
        }

        vm.isValid().observe(viewLifecycleOwner) {
            binding.saveButton.isEnabled = it
        }

        vm.isSaveSuccess().observe(viewLifecycleOwner) {
            if (it) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Snackbar.make(this.requireView(), "Add success!", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(resources.getColor(R.color.primary_variant, null))
                        .setTextColor(resources.getColor(R.color.white, null))
                        .show()
                }
                findNavController().popBackStack()
            }
        }
    }
}