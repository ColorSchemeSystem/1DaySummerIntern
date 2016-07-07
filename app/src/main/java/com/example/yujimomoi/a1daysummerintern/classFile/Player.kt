package com.example.yujimomoi.a1daysummerintern.classFile

import com.example.yujimomoi.a1daysummerintern.classFile.Point

/**
 * Created by yuji.momoi on 2016/07/07.
 */
class Player {
    var point: Point
    var rotation: Int = 0
    var texture_type: Int = 0
    var texture_color: Int = 0

    init {
        this.point = Point()
        this.rotation = 0
        this.texture_type = 0
        this.texture_color = 0
    }

    fun changeTexColor() {
    }

    fun move(amountOfMove: Int) {
    }

    fun moveWithLine(amountOfMove: Int) {
    }

    fun turn(degree: Int) {
    }

    companion object {

        fun changeAllTexColor() {
        }
    }
}
