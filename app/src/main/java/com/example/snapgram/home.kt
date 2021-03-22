package com.example.snapgram

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snapgram.daos.Postdao
import com.example.snapgram.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class home : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: PostAdapter
    private  lateinit var postdao: Postdao

    private lateinit var  addPost : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addPost= findViewById(R.id.addPost)

        recyclerView= findViewById(R.id.recyclerView)


        addPost.setOnClickListener{
             val addPost_intent = Intent(this, CreatePostActivity::class.java)
            startActivity(addPost_intent)
        }

        setUpRecyclerView()



    }

    private fun setUpRecyclerView() {
        postdao= Postdao()
        val postCollections= postdao.postCollections
        val query = postCollections.orderBy("createdAt",Query.Direction.DESCENDING)
        val recyclerViewOPtions =FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

        adapter= PostAdapter(recyclerViewOPtions)
        recyclerView.adapter= adapter
        recyclerView.layoutManager=LinearLayoutManager(this)


    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}