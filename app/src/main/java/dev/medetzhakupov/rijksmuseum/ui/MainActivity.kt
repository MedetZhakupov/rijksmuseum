package dev.medetzhakupov.rijksmuseum.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import dev.medetzhakupov.rijksmuseum.R
import dev.medetzhakupov.rijksmuseum.ui.rijkscollection.RijksCollectionFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.container,
                    RijksCollectionFragment(),
                    RijksCollectionFragment::class.java.canonicalName
                )
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    addToBackStack(null)
                }
            }
        }
    }
}
