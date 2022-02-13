package com.myapp.letusinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        auth = Firebase.auth
        var bt: Button? =findViewById(R.id.a1)

        bt!!.setOnClickListener(View.OnClickListener {
            val editText = findViewById<EditText>(R.id.edt)
            val emailAddress  = editText.text.toString()
            Toast.makeText(baseContext, "$emailAddress" ,Toast.LENGTH_LONG).show()

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Email sent. Check your Email" ,Toast.LENGTH_LONG).show()
                    }
                }

        })

}
}