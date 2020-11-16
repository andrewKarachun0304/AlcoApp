package com.andrewkarachun0304.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        childFragmentNavigate(SearchCocktailFragment())

        val bottomNavigate =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavigate.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.seatch_menu_item -> {
                    childFragmentNavigate(SearchCocktailFragment())
                    true
                }
                R.id.chosen_menu_item-> {
                    childFragmentNavigate(ChosenCocktailFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun childFragmentNavigate(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.child_fragment_container, fragment)
            .commit()
    }
}