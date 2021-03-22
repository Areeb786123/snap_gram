package com.example.snapgram.models

import kotlin.reflect.KClass


data class User(val uid: String = "",
                val displayName: String? = "",
                val imageUrl: String = "",

)