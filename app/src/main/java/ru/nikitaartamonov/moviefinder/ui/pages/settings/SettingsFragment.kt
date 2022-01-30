package ru.nikitaartamonov.moviefinder.ui.pages.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.moviefinder.R
import ru.nikitaartamonov.moviefinder.databinding.FragmentSettingsBinding
import ru.nikitaartamonov.moviefinder.util.MyAnalytics

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyAnalytics.logEvent(requireContext(), "SettingsFragment onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyAnalytics.logEvent(requireContext(), "SettingsFragment onViewCreated()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MyAnalytics.logEvent(requireContext(), "SettingsFragment onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        MyAnalytics.logEvent(requireContext(), "SettingsFragment onDestroy()")
    }
}