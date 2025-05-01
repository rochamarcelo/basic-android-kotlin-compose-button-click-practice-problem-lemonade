package com.example.lemonade

open class LemonadeStage(val imageResource: Int, val textResource: Int, val nextStageIndex: Int) {
    open fun touch(): Boolean {
        return true;
    }
}