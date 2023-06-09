package com.example.kotlin_movie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_movie.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.example.kotlin_movie.ui.favorites.FavoritesFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charge le fragment principal lors de la création de l'activité
        loadFragment(MainFragment())

        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainFragment -> {
                    // Charge le fragment principal lorsque l'élément correspondant est sélectionné
                    loadFragment(MainFragment())
                    true
                }
                R.id.favoritesFragment -> {
                    // Charge le fragment des favoris lorsque l'élément correspondant est sélectionné
                    loadFragment(FavoritesFragment())
                    true
                }
                else -> {
                    // Cas non spécifié
                    false
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}
