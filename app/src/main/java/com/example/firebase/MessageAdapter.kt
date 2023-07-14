package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ReceiveBinding
import com.example.firebase.databinding.SendBinding
import com.google.firebase.auth.FirebaseAuth
import android.view.View

class MessageAdapter(val context:Context,val messageList:ArrayList<Message>):

    RecyclerView.Adapter<RecyclerView.ViewHolder> (){


    val itemReceive=1
    val itemSent=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            itemReceive -> {
                val binding = ReceiveBinding.inflate(layoutInflater, parent, false)
                ReceiveViewHolder(binding)
            }

            else -> {
                val binding = SendBinding.inflate(layoutInflater, parent, false)
                SentViewHolder(binding)
            }


        }
    }

    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
        val currentMessage=messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return itemSent
        }
        else{
            return itemReceive
        }
    }
    override fun getItemCount(): Int {
       return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage=messageList[position]
        if(holder.javaClass==SentViewHolder::class.java){

            val viewHolder=holder as SentViewHolder
            holder.sentMessage.text=currentMessage.message

        }
        else{
            val viewHolder=holder as ReceiveViewHolder
            holder.receiveMessage.text=currentMessage.message
        }
    }
    class SentViewHolder(val binding: SendBinding) : RecyclerView.ViewHolder(binding.root) {
        val sentMessage: TextView = binding.tvsendmessage
    }

    class ReceiveViewHolder(val binding: ReceiveBinding) : RecyclerView.ViewHolder(binding.root) {
        val receiveMessage: TextView = binding.tvreceivemessage
    }




}
