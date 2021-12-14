package com.example.myproject

import android.app.AlarmManager
import android.app.AlertDialog
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var ringtone: Ringtone

        val puan = findViewById<TextView>(R.id.puan)

        val buton = findViewById<Button>(R.id.button)

        val buton2 = findViewById<Button>(R.id.button2)

        val buton3 = findViewById<Button>(R.id.button3)

        val timer = findViewById<TextView>(R.id.timer)

        var sayi = 0

        buton.setOnClickListener() {
            if (timer.text != "Zaman") {
                if (timer.text != "Süre Bitti") {
                    sayi++


                    puan.text = sayi.toString()
                    buton2.text = "BAŞLAT"
                } else {
                    buton.text = "Puanın=" + sayi
                }


            } else {
                buton.text = "Lütfen başlata basınız!"
            }


        }

        buton2.setOnClickListener() {
            if (timer.text == "Zaman" || timer.text == "Süre Bitti") {
                buton.text = "BUTON"
                sayi = 0;
                puan.text = sayi.toString()

                val sayici = object : CountDownTimer(20000, 1000) {


                    override fun onTick(i: Long) {
                        timer.text = "Kalan Süre:${i / 1000} sn"

                    }

                    override fun onFinish() {
                        timer.text = "Süre Bitti"
                        var alarmUri: Uri =
                            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

                        if (alarmUri == null) {
                            alarmUri =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


                        }
                        ringtone = RingtoneManager.getRingtone(this@MainActivity, alarmUri)

                        ringtone.play()

                    }

                }
                sayici.start()

            } else {
                buton2.text = "Süre Bitmedi"


            }


        }

        buton3.setOnClickListener() {
            try {
                if (ringtone.isPlaying) {
                    ringtone.stop()
                }
            }catch (e:UninitializedPropertyAccessException){



            }




        }
    }
}
