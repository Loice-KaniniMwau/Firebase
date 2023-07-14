package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.databinding.ActivityChatBinding
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.Objects

class ChatActivity : AppCompatActivity() {
    val receiverRoom:String?=null
    val senderRoom:String?=null
    lateinit var binding: ActivityChatBinding
    lateinit var mdbRef:DatabaseReference
    lateinit var messageList:ArrayList<Message>
    lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)
        messageList=ArrayList()
        messageAdapter= MessageAdapter(this,messageList)
        binding.rvChats.layoutManager=LinearLayoutManager(this)
        binding.rvChats.adapter=messageAdapter
      mdbRef=FirebaseDatabase.getInstance().getReference()
        val email=intent.getStringExtra("name")
        val receiverUid=intent.getStringExtra("uid")
        val senderUid=FirebaseAuth.getInstance().currentUser?.uid
        val senderRoom=receiverUid+senderUid
       val receiverRoom=senderUid+receiverUid
        supportActionBar?.title=email
        mdbRef.child("chats").child(senderRoom!!).child("messages")
//            .addChildEventListener(object:ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    messageList.clear()
//                  for(shot in snapshot.children){
//                      val message=shot.getValue(Message::class.java)
//                     messageList.add(message!!)
//                  }
//                    messageAdapter.notifyDataSetChanged()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    // Handle changed child if needed
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    // Handle removed child if needed
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    // Handle moved child if needed
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled if needed
                }
            })
        binding.btsend.setOnClickListener {
val message=binding.etmessagebox.text.toString()
      val messageObject=Message(message,senderUid)
            mdbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mdbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            binding.etmessagebox.setText("")
        }
    }
}