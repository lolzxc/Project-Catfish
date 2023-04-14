package com.example.thesis



import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


import androidx.appcompat.app.AppCompatActivity
import com.example.thesis.databinding.ActivityHomeScreenBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigationView = binding.bottomNavigation
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
        val topBar = binding.toolBar
        setSupportActionBar(topBar)
        supportActionBar?.title = "Project Catfish"

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

                    true
                }
                R.id.history -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment()).commit()
                    true
                }
                else -> false
            }
        }


//        binding.userName.text = "Hi, " + Firebase.auth.currentUser?.email

//        binding.signOut.setOnClickListener {

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_bar_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                Firebase.auth.signOut()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


//    private fun checkCurrentUser() {
//        // [START check_current_user]
//        val user = Firebase.auth.currentUser
//        if (user != null) {
//            // User is signed in
//        } else {
//            // No user is signed in
//        }
//        // [END check_current_user]
//    }
//

}

