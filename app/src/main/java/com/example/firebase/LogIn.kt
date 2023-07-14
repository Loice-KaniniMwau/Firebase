package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityLogInBinding

import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
  private  lateinit var auth: FirebaseAuth
   private lateinit var binding:ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }
   private fun initData(){
        clickListener()
    }
   private fun clickListener(){
        binding.tvNewUser.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
            finish()
        }
       binding.btLogin.setOnClickListener {
             getUserData()
       }
    }
    private fun getUserData(){
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            authenticateUser(email, password)
        }
        else{
            Toast.makeText(this,"authentication failed",Toast.LENGTH_LONG).show()
        }
    }
    private fun authenticateUser(email:String,password:String){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                checkResult(it.isSuccessful)

            }
    }
    private fun checkResult(isSuccess:Boolean){
   if(isSuccess){
       startActivity(Intent(this,ChatActivity::class.java))
       finish()
   }
        else{
       Toast.makeText(this,"authentication failed",Toast.LENGTH_LONG).show()
   }
    }

}