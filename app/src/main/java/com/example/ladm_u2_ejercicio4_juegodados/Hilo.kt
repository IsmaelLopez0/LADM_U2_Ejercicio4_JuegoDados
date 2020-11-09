package com.example.ladm_u2_ejercicio4_juegodados

import android.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Hilo(puntero: MainActivity): Thread() {
    private var p = puntero
    private var pausa = false
    var turno = 1 //Turno jugador "1" o jugador "2"
    var ronda = 1
    var infoJ1 = ""
    var infoJ2 = ""
    var random = Random()
    var jugador1 = Jugador()
    var jugador2 = Jugador()

    fun pausar(){
        pausa = !pausa
    }

    override fun run() {
        super.run()
        while(true){
            if(!pausa){
                p.runOnUiThread {
                    if (ronda == 3) {
                        pausar()

                        val totalJ1 = jugador1.getTotal()
                        infoJ1 += "Total: $totalJ1"
                        p.infoJug1.setText(infoJ1)
                        val totalJ2 = jugador2.getTotal()
                        infoJ2 += "Total: $totalJ2"
                        p.infoJug2.setText(infoJ2)

                        when {
                            totalJ1 == totalJ2 -> {
                                AlertDialog.Builder(p) .setTitle("EMPATE")
                                    .setMessage("Ambos jugadores obtuvieron la misma cantidad de números.").show()
                            }
                            totalJ1 > totalJ2 -> {
                                AlertDialog.Builder(p) .setTitle("Ganador")
                                    .setMessage("Jugador 1 es el ganador").show()
                            }
                            else -> {
                                AlertDialog.Builder(p) .setTitle("Ganador")
                                    .setMessage("Jugador 2 es el ganador").show()
                            }
                        }
                    } else {
                        when (turno){
                            1-> {
                                val dado1Jug1 = random.nextInt(1..7)
                                val dado2Jug1 = random.nextInt(1..7)
                                infoJ1 += "Tiro ${ronda}:\n" +
                                        "Dado 1: $dado1Jug1\n" +
                                        "Dado 2: $dado2Jug1\n\n"
                                jugador1.sumarPuntos(dado1Jug1, dado2Jug1)
                                p.infoJug1.setText(infoJ1)
                                turno = 2
                            }
                            2 -> {
                                val dado1Jug2 = random.nextInt(1..7)
                                val dado2Jug2 = random.nextInt(1..7)
                                infoJ2 += "Tiro ${ronda}:\n" +
                                        "Dado 1: $dado1Jug2\n" +
                                        "Dado 2: $dado2Jug2\n\n"
                                jugador2.sumarPuntos(dado1Jug2, dado2Jug2)
                                p.infoJug2.setText(infoJ2)
                                turno = 1
                                ronda += 1
                            }
                        }
                    }
                }
            }
            sleep(1500L)
        }
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    fun reset(){
        ronda = 1
        turno = 1
        jugador1.reset()
        jugador2.reset()
        pausar()
        infoJ1 = ""
        infoJ2 = ""
    }

}