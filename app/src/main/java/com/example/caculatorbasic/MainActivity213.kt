package com.example.caculatorbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var textStr: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_ce.setOnClickListener {
            onClickBtnDeleteAll()
        }
        btn_delete.setOnClickListener {
            onClickBtnDeleteOne()
        }
        btn_plus_and_sub.setOnClickListener {
            onClickBtnPlusAndSub()
        }
        btn_dot.setOnClickListener {
//            onClickBtnDot()
        }

        btn_plus.setOnClickListener{
            onClickBtnPlus()
        }
    }

    override fun onResume() {
        super.onResume()
        edit_text_input.showSoftInputOnFocus = false
        edit_text_input.isFocusable = true
        edit_text_input.setSelection(1, 1)
    }

    private fun onClickBtnDeleteAll() {
        edit_text_input.text = Editable.Factory.getInstance().newEditable("0")
        edit_text_input.isFocusable = true
        edit_text_input.setSelection(1, 1)
    }

    private fun onClickBtnDeleteOne() {
        val selectionEnd = edit_text_input.selectionEnd
        if (edit_text_input.text.length == 1) {
            onClickBtnDeleteAll()
            return
        }
        if (selectionEnd == 0) return
        val selectionStart = edit_text_input.selectionStart
        textStr = edit_text_input.text.toString()
        edit_text_input.text = Editable.Factory.getInstance()
            .newEditable(
                textStr.substring(0, selectionStart - 1) + textStr.substring(
                    selectionEnd
                )
            )
        edit_text_input.setSelection(selectionStart - 1)
    }

    private fun onClickBtnChangeTextView(str: String) {
        val selectionEnd = edit_text_input.selectionEnd
        val selectionStart = edit_text_input.selectionStart
        textStr = edit_text_input.text.toString()
        edit_text_input.text = Editable.Factory.getInstance().newEditable(
            textStr.substring(
                0,
                selectionStart
            ) + str + textStr.substring(selectionEnd, textStr.length)
        )

        textStr = edit_text_input.text.toString()
        if ("^(0\\d)".toRegex().matches(textStr)) {
            edit_text_input.text = Editable.Factory.getInstance().newEditable(textStr.substring(1))
            edit_text_input.setSelection(selectionStart)
        } else if ("^(-0\\d)".toRegex().matches(textStr)) {
            edit_text_input.text = Editable.Factory.getInstance().newEditable(textStr.substring(2))
            edit_text_input.setSelection(if (selectionStart - 1 < 0) 0 else selectionStart - 1)
        } else
            edit_text_input.setSelection(if (selectionStart + str.length > textStr.length) textStr.length else selectionStart + str.length)
    }

    fun onClickNumber(view: View) {
        val numberStr = findViewById<Button>(view.id).text.toString()
        onClickBtnChangeTextView(numberStr)
    }

    private fun onClickBtnPlusAndSub() {
        textStr = edit_text_input.text.toString()
        if (textStr == "0") {
            return
        }
        edit_text_input.text = Editable.Factory.getInstance().newEditable(
            (textStr.toFloat() * -1).toString()
        )
    }

    private fun onClickBtnDot() {
        textStr = edit_text_input.text.toString()
        if ("/./gi".toRegex().matches(textStr)) {
            return
        }
        if (edit_text_input.selectionEnd == 1 && textStr == "0") {
            edit_text_input.text = Editable.Factory.getInstance().newEditable("0.")
            edit_text_input.setSelection(2)
            return
        }
        onClickBtnChangeTextView(".")
    }

    fun onClickBtnPlus(){
        val lastChar = edit_text_input.text.toString()
        onClickBtnChangeTextView("+")
        Log.d("TAG","What?")
    }
}