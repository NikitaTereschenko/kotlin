package com.example.shopapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

class programData : AppCompatActivity() {

    //var db = DbHelper(this, null)
    companion object : AppCompatActivity() {
        var userName: String = "UserName"
        var adminFlag: Boolean = false

        @SuppressLint("StaticFieldLeak")
        var db: DbHelper? = null
        fun getAllItems(): ArrayList<items>? {
            //var db = DbHelper(this, null)
            var items = db?.getItems()
            return items
        }

        fun getTenItems(): ArrayList<items>? {
            var items = db?.getTenItems()
            return items
        }

        fun getAllOrders(): ArrayList<orders>? {
            var items = db?.getOrders(userName)
            return items
        }

        fun deleteObject(name: String) {
            db?.delItem(name)
        }

        fun isExistItemName(name : String) : String? {
            return db?.isExistItemName(name)
        }

        fun addObject(name: String, amount: Int, image: String) {
            db?.addItem(name, image, amount)
        }
    }
}