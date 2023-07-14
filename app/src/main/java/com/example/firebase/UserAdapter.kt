package com.example.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.UserlayoutBinding
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(private val context: Context, var userArray:ArrayList<User>):RecyclerView.Adapter<UserViewHolder>() {
    lateinit var binding: UserlayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val binding=UserlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userArray.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    val currentUser=userArray[position]
        val binding=holder.binding
        binding.tvUsername.text=currentUser.email.toString()
        holder.itemView.setOnClickListener {
      val intent=Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentUser?.uid)
            intent.putExtra("email",currentUser.email)
            context.startActivity(intent)


        }
    }
}
class UserViewHolder(var binding: UserlayoutBinding):RecyclerView.ViewHolder(binding.root){

}