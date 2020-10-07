package com.example.caculatorbasic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_delete.setOnClickListener {
            onClickBtnDelete()
        }
//        btn_number_0.setOnClickListener {
//            onClickNumber(0)
//        }
//        btn_number_1.setOnClickListener {
//            onClickNumber(1)
//        }

    }

    override fun onResume() {
        super.onResume()
        edit_text_input.showSoftInputOnFocus = false
        edit_text_input.focusable = View.FOCUSABLE
        edit_text_input.setSelection(1, 1)
    }

    private fun onClickBtnDelete() {
        edit_text_input.text = Editable.Factory.getInstance().newEditable("0")
        edit_text_input.isFocusable = true
        edit_text_input.setSelection(1, 1)
    }

    fun onClickNumber(view: View) {
        val numberStr = findViewById<Button>(view.id).text.toString()
        val selectionEnd = edit_text_input.selectionEnd
        val selectionStart = edit_text_input.selectionStart
        val textString = edit_text_input.text.toString()
        edit_text_input.text = Editable.Factory.getInstance().newEditable(
            textString.substring(
                0,
                selectionStart
            ) + numberStr + textString.substring(selectionEnd, textString.length)
        )
        val afterSelection =
            if (selectionStart + 1 > edit_text_input.text.length) edit_text_input.text.length else selectionStart + 1
        edit_text_input.setSelection(afterSelection, afterSelection)
    }

}