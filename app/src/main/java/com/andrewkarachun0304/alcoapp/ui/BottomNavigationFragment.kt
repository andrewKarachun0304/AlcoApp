package com.andrewkarachun0304.alcoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.andrewkarachun0304.alcoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bottomNavigate =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.child_fragment_container) as NavHostFragment
         NavigationUI.setupWithNavController(bottomNavigate, navHostFragment.navController)
    }
}