package com.myapp.letusinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.Handler
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class SplashScreen1 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen1)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        var myemail: String = ""
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
        }

        auth = Firebase.auth
        supportActionBar?.hide()
        database = FirebaseFirestore.getInstance()
        if (user != null) {


            database!!.collection("company").document(myemail).get()
                .addOnSuccessListener { document->
                    if (document.exists()  ==true) {
                        val intent = Intent(this, CampanyDashboard::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(this, CandidateDashboard::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        } else {

            Handler().postDelayed({


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000) // 3000 is the delayed time in milliseconds.
        }

    }
}