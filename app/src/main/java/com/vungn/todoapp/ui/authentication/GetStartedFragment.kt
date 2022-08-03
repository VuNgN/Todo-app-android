package com.vungn.todoapp.ui.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vungn.todoapp.R
import com.vungn.todoapp.databinding.FragmentGetStartedBinding
import com.vungn.todoapp.util.extention.WindowEx.hideStatusBar

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class GetStartedFragment : Fragment() {
    private lateinit var getStartedButton: Button
    private var _binding: FragmentGetStartedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().hideStatusBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingView()
        handleEvents()
    }

    private fun handleEvents() {
        getStartedButton.setOnClickListener {
            findNavController().navigate(R.id.action_getStartedFragment_to_onBoardFragment, null)
        }
    }

    private fun bindingView() {
        getStartedButton = binding.getStartedButton
    }
}