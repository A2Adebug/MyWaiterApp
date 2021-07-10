package com.example.mywaiterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        RegBtnReg.setOnClickListener(){
            val intent = Intent(applicationContext, Advert::class.java)
            startActivity(intent)
        }
    }
}