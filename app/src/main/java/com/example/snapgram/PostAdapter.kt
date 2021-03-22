package com.example.snapgram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snapgram.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.withContext

class PostAdapter(options: FirestoreRecyclerOptions<Post>) :FirestoreRecyclerAdapter<Post,PostAdapter.PostViewHolder>
(options)
{

    class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val postText:TextView= itemView.findViewById(R.id.postTitle)
        val userText:TextView = itemView.findViewById(R.id.userName)
        val createdAt:TextView= itemView.findViewById(R.id.createdAt)
        val likeCount:TextView=itemView.findViewById(R.id.likeCount)
        val userImage:ImageView=itemView.findViewById(R.id.userImage)
        val likeButton:ImageView= itemView.findViewById(R.id.likeButton)
        val user_post_image:ImageView=itemView.findViewById(R.id.user_post_image)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.postText.text = model.text
        holder.userText.text = model.createdBy.displayName
        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)
        holder.likeCount.text = model.likedBy.size.toString()
        holder.createdAt.text = Utills.getTimeAgo(model.createdAt)
        Glide.with(holder.user_post_image).load(model.createdBy.imageUrl).into(holder.user_post_image)

    }
}