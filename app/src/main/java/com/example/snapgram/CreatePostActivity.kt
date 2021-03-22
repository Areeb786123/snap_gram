package com.example.snapgram

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.example.snapgram.daos.Postdao

class CreatePostActivity : AppCompatActivity() {
    private lateinit var postImagei:ImageView
    private lateinit var post_btn : Button
    private lateinit var addimage_btn:ImageButton
    lateinit var  filepath:Uri

    private lateinit var  postInput :EditText

    private lateinit var postdao:Postdao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        post_btn= findViewById(R.id.post_btn)
        postInput = findViewById(R.id.postInput)
        addimage_btn=findViewById(R.id.image_btn)
        postImagei=findViewById(R.id.postImagei)

        addimage_btn.setOnClickListener{
            val intent = Intent()
            intent.type="image/*"
            intent.action =Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"select Image "),1)
        }

        postdao = Postdao()

        post_btn.setOnClickListener {

            val input = postInput.text.toString().trim()
            if(input.isNotEmpty()){
                postdao.addPost(input)
                Toast.makeText(this,"Post have been uploaded",Toast.LENGTH_LONG).show()
                val intent= Intent(this,home::class.java)
                startActivity(intent)


            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode ==Activity.RESULT_OK){
            if (data != null){
                postImagei?.setImageURI(data.data)
            }

        }
    }




}