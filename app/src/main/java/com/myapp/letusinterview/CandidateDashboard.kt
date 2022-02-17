package com.myapp.letusinterview

import android.Manifest
import android.Manifest.permission.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

public var counter: Int =0

class CandidateDashboard : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
   lateinit var mRef:DatabaseReference
    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_dashboard)

        var myemail = ""
        var id=""
        val user = Firebase.auth.currentUser
        user?.let {

            myemail = user.email.toString()
//            id=user.uid.toString()
        }
        mRef = database.getReference(id)


    }
    override fun onStart() {
        super.onStart()
        if (hasNoPermissions()) {
            requestPermission()
        }
    }
    fun hasNoPermissions(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, permissions,0)
    }

    fun nextscreen(view: View) {
        val intent= Intent(this,viewqcandidate::class.java)
        startActivity(intent)
    }

    fun switchcompany(view: View) {

        val intent =Intent(this, switchcom::class.java)
  startActivity(intent)
    }


}