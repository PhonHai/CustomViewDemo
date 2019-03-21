package com.haiphon.customviewdemo.plugins

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haiphon.customviewdemo.R
import com.haiphon.customviewdemo.bean.MultiGroupHistogramChildData
import com.haiphon.customviewdemo.bean.MultiGroupHistogramGroupData
import com.haiphon.customviewdemo.utils.LogUtil
import kotlinx.android.synthetic.main.activity_multigrouphistogram.*
import java.util.*


class MultiGroupHistogramViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.haiphon.customviewdemo.R.layout.activity_multigrouphistogram)
        initMultiGroupHistogramView()
    }

    private fun initMultiGroupHistogramView() {
        val random = Random()
        val groupSize = random.nextInt(5) + 10
        val groupDataList : MutableList<MultiGroupHistogramGroupData> = mutableListOf()
        // 生成测试数据
        for (i in 0 until groupSize) {
            val childDataList : MutableList<MultiGroupHistogramChildData> = mutableListOf() // 可改值得list
            val groupData = MultiGroupHistogramGroupData()
            groupData.groupName = "第" + (i + 1) + "组"
            val childData1 = MultiGroupHistogramChildData()
            childData1.suffix = "分"
            childData1.value = getRandomFloatNum( 50, 0)
            childDataList.add(childData1)

            val childData2 = MultiGroupHistogramChildData()
            childData2.suffix = "%"
            childData2.value = getRandomFloatNum( 50, 6)
            childDataList.add(childData2)
            groupData.childDataList = childDataList
            groupDataList.add(groupData)
        }
        multigroup_histogramview.setDataList(groupDataList)
        val color1 = intArrayOf(resources.getColor(R.color.color_orange), resources.getColor(R.color.colorPrimary))

        val color2 = intArrayOf(
            resources.getColor(R.color.color_supper_tip_normal),
            resources.getColor(R.color.bg_supper_selected)
        )
        // 设置直方图颜色
        multigroup_histogramview.setHistogramColor(color1, color2)
    }

    fun getRandomFloatNum(range : Int, decimal: Int) : Float {
        val floatRandomNum =  Random().nextInt(range) + Random().nextFloat()
        // 0则不带小数点，6则带6位小数点，不在这个范围都是2个小数点
        val mDecimal = if(decimal in 0..6) decimal else 2
        LogUtil.d(String.format("%." + mDecimal + "f", floatRandomNum))
        return String.format("%." + mDecimal + "f", floatRandomNum).toFloat()
    }




}
