package com.example.shopapp.Models

class OrderModel {
    private var login : String = ""
    private var amount : String = ""
    private var date : String = ""


    constructor()
    constructor(login : String = "", amount : String = "", date : String = "") {
        this.login = login
        this.amount = amount
        this.date = date
    }

    fun getLogin() : String {
        return this.login
    }

    fun getAmount() : String {
        return this.amount
    }

    fun getDate() : String {
        return this.date
    }

    fun setLogin(login: String) {
        this.login = login
    }

    fun setAmount(amount: String) {
        this.amount = amount
    }

    fun setDate(date : String) {
        this.date = date
    }
}