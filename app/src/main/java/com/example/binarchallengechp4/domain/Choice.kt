package com.example.binarchallengechp4.domain

enum class Choice(val index: Int) {
    BATU(0),
    KERTAS(1),
    GUNTING(2);
    companion object {
        fun getValueFromIndex(index: Int) = values()[index]
    }
}