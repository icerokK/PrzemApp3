package com.example.przemapp3

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import kotlin.math.abs

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private fun loadFragment(containerView: Int = R.id.fragmentContainerView, fragment: Fragment, direction: String = "left"){
        if(direction=="right"){
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(

                    R.anim.left_to_right,
                    R.anim.left_to_right2,
                    0,
                    0
                )
                .replace(containerView, fragment)
                .commitNow()
        }else{
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(

                    R.anim.right_to_left,
                    R.anim.right_to_left2,
                    0,
                    0
                )
                .replace(containerView, fragment)
                .commitNow()
        }
    }

    var x1 = 0.0f
    var x2 = 0.0f
    var y1 = 0.0f
    var y2 = 0.0f

    lateinit var gestureDetector: GestureDetector

    companion object{
        const val MIN_DISTANCE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this,this)
    }

    override fun onDown(e: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
//        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
//        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)
        when(event?.action){
            0 ->{
                x1 = event.x
                y1 = event.y
            }
            1 ->{
                x2 = event.x
                y2 = event.y

                val valueX = x2-x1
                val valueY = y2-y1

                if (abs(valueX) > MIN_DISTANCE){
                    if (x2>x1){
                        Toast.makeText(this, supportFragmentManager.findFragmentById(R.id.fragmentContainerView).toString().substringBefore('{'), Toast.LENGTH_SHORT).show()
                        when(supportFragmentManager
                            .findFragmentById(R.id.fragmentContainerView)
                            .toString()
                            .substringBefore('{')) {
                            null -> loadFragment(R.id.fragmentContainerView, BlankFragment(), "right")
                            "BlankFragment" -> loadFragment(R.id.fragmentContainerView, BlankFragment2(), "right")
                            "BlankFragment2" -> loadFragment(R.id.fragmentContainerView, BlankFragment3(), "right")
                            "BlankFragment3" -> loadFragment(R.id.fragmentContainerView, BlankFragment(), "right")
                        }
                    } else {
                        Toast.makeText(this, supportFragmentManager.findFragmentById(R.id.fragmentContainerView).toString().substringBefore('{'), Toast.LENGTH_SHORT).show()
//                        when(supportFragmentManager.findFragmentById(R.id.fragmentContainerView).toString().substringBefore('{'))
                        when(supportFragmentManager.findFragmentById(R.id.fragmentContainerView).toString().substringBefore('{'))
                        {
                            null -> loadFragment(R.id.fragmentContainerView, BlankFragment3())
                            "BlankFragment" -> loadFragment(R.id.fragmentContainerView, BlankFragment3())
                            "BlankFragment2" -> loadFragment(R.id.fragmentContainerView, BlankFragment())
                            "BlankFragment3" -> loadFragment(R.id.fragmentContainerView, BlankFragment2())
                        }
                    }
                } else if (abs(valueY)> MIN_DISTANCE) {
                    if (y2>y1)
                    {
                        Toast.makeText(this, "swiped to bottom", Toast.LENGTH_SHORT).show()
                    } else
                    {
                        Toast.makeText(this, "swiped to top", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
}