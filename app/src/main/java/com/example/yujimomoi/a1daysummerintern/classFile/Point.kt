package com.example.yujimomoi.a1daysummerintern.classFile

/**
 * Created by yuji.momoi on 2016/07/07.
 */
class Point {
    var x: Int = 0
    var y: Int = 0

    constructor() {
        this.x = 0
        this.y = 0
    }

    internal constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun SetPoint(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}
