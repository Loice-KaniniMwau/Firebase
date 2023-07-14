package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
  private lateinit var userRecyclerView: RecyclerView
  private lateinit var userList: ArrayList<User>
  private lateinit var auth: FirebaseAuth
  private  lateinit var mDRef:DatabaseReference
  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      auth= FirebaseAuth.getInstance()
        mDRef=FirebaseDatabase.getInstance().getReference()
        userList= ArrayList()
        adapter=UserAdapter(this,userList)
        userRecyclerView=binding.rvUsers
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.adapter=adapter
        mDRef.child("user").addValueEventListener(object :ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
         for(snap in snapshot.children){
    val currentUser=snap.getValue(User::class.java)
             if(auth.currentUser?.uid!=currentUser?.uid){
                 userList.add(currentUser!!)
             }

     }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
     initData()

//        inflate the menu


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        if(item.itemId==R.id.logout){
            auth.signOut()
            startActivity(Intent(this,LogIn::class.java))
            finish()
            return true
        }
        return true
    }
    private fun initData(){
        auth= FirebaseAuth.getInstance()
        checkIfUserIsLoggedIn()
    }
    private fun checkIfUserIsLoggedIn(){
        val currentUser=auth.currentUser
        if(currentUser !=null){
            startActivity(Intent(this,LogIn::class.java))
            finish()
        }
        else{
            startActivity(Intent(this,LogIn::class.java))
            finish()
        }

    }

}