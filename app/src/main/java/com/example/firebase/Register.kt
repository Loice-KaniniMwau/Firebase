package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.StringBuilder

class Register : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    lateinit var binding: ActivityRegisterBinding
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }
   private fun initData(){
        auth= FirebaseAuth.getInstance()
       clickListener()
    }
    private fun clickListener(){
binding.btLogin.setOnClickListener {
createUser()
}
        binding.tvhaveaccount.setOnClickListener {

        }
    }
    private  fun createUser(){
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var passwordConfirmation=binding.etConfrimPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()){
            if(password==passwordConfirmation){
                saveUser(email,password)

            }
            else{
                Toast.makeText(this,"password mismatch",Toast.LENGTH_LONG).show()
            }

        }
        else{
            Toast.makeText(this,"all inputs required",Toast.LENGTH_LONG).show()
        }
    }
    private fun saveUser(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)

            .addOnCompleteListener {
                if (it.isSuccessful){
                    addUserToDatabase(email,password,auth.currentUser?.uid!!)
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }

            }

    }

    private fun addUserToDatabase( email: String, password: String,uid:String){
database=FirebaseDatabase.getInstance().getReference()
    database.child("user").child(uid).setValue(User(email,password,uid))
    }
}