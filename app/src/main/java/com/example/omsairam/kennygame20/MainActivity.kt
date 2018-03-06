package com.example.omsairam.kennygame20

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0

        imageArray = arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9)

        hideImages()

        object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                    retry_again()
            }

            override fun onTick(p0: Long) {
                timeText.text = "Time: " + p0 / 1000
            }

        }.start()



    }
    fun retry_again(){
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Try Again?")
        alert.setMessage("Wanna Play Again??")
        alert.setPositiveButton("Yes"){ dialoginterface : DialogInterface, i -> Intent(applicationContext, MainActivity::class.java) }
        alert.setNegativeButton("NO"){ dialogInterface : DialogInterface, i :Int -> Toast.makeText(applicationContext, "Comeagain!!",Toast.LENGTH_SHORT).show()
        }
        alert.show()

    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)

            }

        }

        handler.post(runnable)

    }


    fun increaseScore(view: View) {
        score++

        scoreText.text = "Score: " + score
    }

}

