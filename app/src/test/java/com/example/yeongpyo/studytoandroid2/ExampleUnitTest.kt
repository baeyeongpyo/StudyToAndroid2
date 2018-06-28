package com.example.yeongpyo.studytoandroid2

import org.junit.Test
import java.util.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

    }
}

class injectTest{
    private var maker : NumberMaker = NumberMaker()
    constructor(className : NumberMaker){
        maker = className
    }

}

class NumberMaker{
    fun RandomNumber() : Int = Random().nextInt(100)
}

