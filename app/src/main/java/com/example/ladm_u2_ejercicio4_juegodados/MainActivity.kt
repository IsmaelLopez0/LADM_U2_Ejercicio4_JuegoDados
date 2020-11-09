package com.example.ladm_u2_ejercicio4_juegodados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var hilo = Hilo(this)
    var iniciado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        btnTirar.setOnClickListener{
            if(!iniciado) {
                hilo.start()
                iniciado = true
            } else {
                hilo.reset()
                infoJug1.setText("")
                infoJug2.setText("")
            }
        }
    }
}