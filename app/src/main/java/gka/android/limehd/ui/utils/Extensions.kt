package gka.android.limehd.ui.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        applicationWindowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}