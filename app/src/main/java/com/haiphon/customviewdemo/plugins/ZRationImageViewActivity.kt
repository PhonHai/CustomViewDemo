package com.haiphon.customviewdemo.plugins

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.haiphon.customviewdemo.R
import kotlinx.android.synthetic.main.activity_zration.*
import java.util.*

class ZRationImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zration)

        val list = mutableListOf<String>("1.4","2.0","1.7","0.8","0.7")
        ig_first.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            // 随机取一个数值刷新图片
                list.shuffled().take(1).forEach{
                    ig_first.setRation(it.toString().toFloat())
                    ig_first.requestLayout()
                }

            }
        })
    }

}
