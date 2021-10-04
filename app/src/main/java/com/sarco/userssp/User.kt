package com.sarco.userssp

//clase especializada en modelo de datos para kotlin.
//se usa la palabra reservada data antes de class para definirlo.
data class User(val id: Long, var name: String, var lastName: String, var url: String) {


}