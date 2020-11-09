package com.example.ladm_u2_ejercicio4_juegodados

class Jugador() {
    private var total = 0

    fun sumarPuntos(dado1: Int, dado2: Int){
        total += dado1 + dado2
    }

    fun getTotal(): Int{
        return total
    }

    fun reset(){
        total = 0
    }
}