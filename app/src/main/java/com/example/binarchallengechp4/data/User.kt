package com.example.binarchallengechp4.data

import android.widget.ImageView

abstract class User {
    lateinit var name: String
    var player: Int = -1
    lateinit var choice: ArrayList<ImageView>
}