package com.tasty.recipesapp.activity


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationBarView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        Log.d(TAG, "onCreate() called in $TAG")


        //replaceFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    return@OnItemSelectedListener true
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    return@OnItemSelectedListener true
                }

                R.id.recipesFragment -> {
                    navController.navigate(R.id.recipesFragment)
                    return@OnItemSelectedListener true
                }

                else -> true
            }
        })

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }


        //val result = intent.getStringExtra("message")
        //Log.d(TAG, "$TAG result $result")

        // binding.outputText.text = result
    }

    //    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.navHostFragment, fragment)
//        transaction.commit()
//    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called in $TAG")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called in $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called in $TAG")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called in $TAG")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called in $TAG")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called in $TAG")
    }
}