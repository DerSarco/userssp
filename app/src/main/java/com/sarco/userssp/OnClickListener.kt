package com.sarco.userssp

//Se crea la interfaz que contendra las funciones que le entregaremos a los elementos de la lista
interface OnClickListener {

    fun onClick(user:User, position: Int)
}