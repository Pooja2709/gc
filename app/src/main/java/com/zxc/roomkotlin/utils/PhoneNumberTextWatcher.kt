package com.zxc.roomkotlin.utils

import android.text.Editable
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.widget.EditText
import android.text.TextWatcher


class PhoneNumberTextWatcher(private val editText: EditText) : TextWatcher {

    override fun onTextChanged(
        s: CharSequence, cursorPosition: Int, before: Int,
        count: Int
    ) {
        var cursorPosition = cursorPosition

        if (before == 0 && count == 1) {  //Entering values

            var `val`: String? = s.toString()
            var a: String? = ""
            var b: String? = ""
            var c: String? = ""
            if (`val` != null && `val`.length > 0) {
                `val` = `val`.replace("-", "")
                if (`val`.length >= 3) {
                    a = `val`.substring(0, 3)
                } else if (`val`.length < 3) {
                    a = `val`.substring(0, `val`.length)
                }
                if (`val`.length >= 6) {
                    b = `val`.substring(3, 6)
                    c = `val`.substring(6, `val`.length)
                } else if (`val`.length > 3 && `val`.length < 6) {
                    b = `val`.substring(3, `val`.length)
                }
                val stringBuffer = StringBuffer()
                if (a != null && a.length > 0) {
                    stringBuffer.append(a)

                }
                if (b != null && b.length > 0) {
                    stringBuffer.append("-")
                    stringBuffer.append(b)

                }
                if (c != null && c.length > 0) {
                    stringBuffer.append("-")
                    stringBuffer.append(c)
                }
                editText.removeTextChangedListener(this)
                editText.setText(stringBuffer.toString())
                if (cursorPosition == 3 || cursorPosition == 7) {
                    cursorPosition = cursorPosition + 2
                } else {
                    cursorPosition = cursorPosition + 1
                }
                if (cursorPosition <= editText.text.toString().length) {
                    editText.setSelection(cursorPosition)
                } else {
                    editText.setSelection(editText.text.toString().length)
                }
                editText.addTextChangedListener(this)
            } else {
                editText.removeTextChangedListener(this)
                editText.setText("")
                editText.addTextChangedListener(this)
            }

        }

        if (before == 1 && count == 0) {  //Deleting values

            var `val`: String? = s.toString()
            var a: String? = ""
            var b: String? = ""
            var c: String? = ""

            if (`val` != null && `val`.length > 0) {
                `val` = `val`.replace("-", "")
                if (cursorPosition == 3) {
                    `val` = removeCharAt(`val`, cursorPosition - 1, s.toString().length - 1)
                } else if (cursorPosition == 7) {
                    `val` = removeCharAt(`val`, cursorPosition - 2, s.toString().length - 2)
                }
                if (`val`.length >= 3) {
                    a = `val`.substring(0, 3)
                } else if (`val`.length < 3) {
                    a = `val`.substring(0, `val`.length)
                }
                if (`val`.length >= 6) {
                    b = `val`.substring(3, 6)
                    c = `val`.substring(6, `val`.length)
                } else if (`val`.length > 3 && `val`.length < 6) {
                    b = `val`.substring(3, `val`.length)
                }
                val stringBuffer = StringBuffer()
                if (a != null && a.length > 0) {
                    stringBuffer.append(a)

                }
                if (b != null && b.length > 0) {
                    stringBuffer.append("-")
                    stringBuffer.append(b)

                }
                if (c != null && c.length > 0) {
                    stringBuffer.append("-")
                    stringBuffer.append(c)
                }
                editText.removeTextChangedListener(this)
                editText.setText(stringBuffer.toString())
                if (cursorPosition == 3 || cursorPosition == 7) {
                    cursorPosition = cursorPosition - 1
                }
                if (cursorPosition <= editText.text.toString().length) {
                    editText.setSelection(cursorPosition)
                } else {
                    editText.setSelection(editText.text.toString().length)
                }
                editText.addTextChangedListener(this)
            } else {
                editText.removeTextChangedListener(this)
                editText.setText("")
                editText.addTextChangedListener(this)
            }

        }


    }

    override fun beforeTextChanged(
        s: CharSequence, start: Int, count: Int,
        after: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {


    }

    companion object {

        private val TAG = "PhoneNumberTextWatcher"

        fun removeCharAt(s: String, pos: Int, length: Int): String {

            var value = ""
            if (length > pos) {
                value = s.substring(pos + 1)
            }
            return s.substring(0, pos) + value
        }
    }
}