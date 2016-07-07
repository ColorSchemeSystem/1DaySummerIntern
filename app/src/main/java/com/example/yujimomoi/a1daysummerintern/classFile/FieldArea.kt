package com.example.yujimomoi.a1daysummerintern.classFile

/**
 * Created by yuji.momoi on 2016/07/07.
 */
class FieldArea {
    var fieldWidth: Int = 0
        private set
    var fieldHeight: Int = 0
        private set
    private val line_color: String?

    init {
        fieldWidth = 0
        fieldHeight = 0
        line_color = null
    }

    fun initField(width: Int, height: Int) {
        fieldWidth = width
        fieldHeight = height
    }

    fun initFielid() {
    }

    val fieldSise: FieldArea?
        get() = null

    fun getFieldSize() {
    }

    fun drawLine(point: Point) {
    }
}
