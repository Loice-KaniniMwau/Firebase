package com.example.firebase
//package com.example.firebase
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.firebase.databinding.ActivityDashboardBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class Dashboard : AppCompatActivity() {
//    private lateinit var auth: FirebaseAuth
//    private lateinit var binding: ActivityDashboardBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding= ActivityDashboardBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initData()
//    }
//
//   private fun initData( ){
//        auth= FirebaseAuth.getInstance()
//        setUserEmail()
//       clickListener()
//
//    }
//    private fun clickListener(){
//        binding.btSignOut.setOnClickListener {
//            auth.signOut()
//            startActivity(Intent(this,LogIn::class.java))
//            finish()
//        }
//    }
//    private fun getCurrentUser():String? {
//        return auth.currentUser?.email
//    }
//    private fun setUserEmail(){
//        binding.tvUserEmail.text="welcome" +getCurrentUser()
//    }
//}