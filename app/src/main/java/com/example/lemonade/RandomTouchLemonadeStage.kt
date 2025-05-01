package com.example.lemonade

class RandomTouchLemonadeStage constructor(imageResource: Int, textResource: Int, nextStageIndex: Int) : LemonadeStage(imageResource = imageResource, textResource = textResource, nextStageIndex = nextStageIndex) {
    private var touchNumberTarget: Int = 0;
    private var totalTouched = 0;
    override fun touch(): Boolean {
        if (touchNumberTarget == 0) {
            touchNumberTarget = (2..4).random();
        }
        totalTouched++;
        if (totalTouched >= touchNumberTarget) {
            touchNumberTarget = 0;
            totalTouched = 0;

            return true;
        }

        return false;
    }
}