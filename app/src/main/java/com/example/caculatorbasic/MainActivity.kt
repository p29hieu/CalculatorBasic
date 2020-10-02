package com.example.caculatorbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()
    edit_text_input.inputType = InputType.TYPE_NULL
    edit_text_input.requestFocus()
  }
}