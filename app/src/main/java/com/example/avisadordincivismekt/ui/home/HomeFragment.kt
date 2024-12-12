package com.example.avisadordincivismekt.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avisadordincivismekt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private val locationPermissionRequest = null

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textHome
    homeViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

    return root
  }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(requireContext(), "Request permissions", Toast.LENGTH_SHORT).show()
            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        } else {
            Toast.makeText(requireContext(), "getLocation: permissions granted", Toast.LENGTH_SHORT).show()
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}