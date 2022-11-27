package com.example.myapplication.View

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.R

class RelationCustomView: LinearLayout {

    constructor(context: Context): super(context) {

        initView()
    }

    private fun initView() {
        inflate(context, R.layout.ll_relation_custom_view, this)
    }
}