package com.example.yeongpyo.studytoandroid2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var test : daggerfunction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerdaggerCompat.builder().build().inject(this)
        println(test.randomNumber())
    }
}
@Module
class daggerfunction @Inject constructor(){
    @Provides
    fun randomNumber() : Int = Random().nextInt(100)
}
@Component(modules = arrayOf(daggerfunction::class))
interface daggerCompat{
    fun inject(app: MainActivity)
}

