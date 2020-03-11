package com.app.sample.customs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.app.sample.R

class CustomProgressDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading_dialog)
        setCancelable(false)
    }
}