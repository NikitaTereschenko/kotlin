package com.example.shopapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class DbHelper(val context : Context, val factory : SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "app2", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, password TEXT, isAdmin INT)"
        db!!.execSQL(query, )
        query = "CREATE TABLE items (id INT PRIMARY KEY, name TEXT, description TEXT, image TEXT, price INT, ordersCount INT)"
        db!!.execSQL(query, )
        query = "CREATE TABLE orders (id INT PRIMARY KEY, login TEXT, name TEXT, sum TEXT, date TEXT)"
        db!!.execSQL(query, )

        val values = ContentValues()
        values.put("login", "admin")
        values.put("password", "admin")
        values.put("isAdmin", 1)
        //val db = this.writableDatabase
        db.insert("users", null, values)
        //db.close()

        /*val db2 = this.writableDatabase
        val values2 = ContentValues()
        values2.put("id", 0)
        values2.put("name", "testItem")
        values2.put("description", "testItem")
        values2.put("image", "item1")
        values2.put("price", 1000)
        values2.put("ordersCount", 10)
        db2.insert("items", null, values2)
        db2.close()*/
        //setDefaultLabel(db)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        db!!.execSQL("DROP TABLE IF EXISTS items")
        db!!.execSQL("DROP TABLE IF EXISTS orders")
        onCreate(db)
    }

    fun isExistItemName(name : String) : String {
        val db = this.readableDatabase
        var name2 = name
        var index = 0
        var result = db.rawQuery("SELECT * FROM items WHERE name = '$name2'", null)
        while (result.moveToFirst()) {
            index++
            name2 = name + index.toString()
            result = db.rawQuery("SELECT * FROM items WHERE name = '$name2'", null)
        }
        return name2
    }

    fun addItem(name : String, image : String, price : Int) {
        var index = 0
        val db = this.readableDatabase
        var name2 = name
        //var name2 = isExistItemName(name)


        val values2 = ContentValues()
        //values2.put("id", 0)
        values2.put("name", name2)
        values2.put("description", "testItem")
        values2.put("image", image)
        values2.put("price", price)
        values2.put("ordersCount", 1)
        db.insert("items", null, values2)
    }

    fun addUser(login: String, password: String) {
        val values = ContentValues()
        values.put("login", login)
        values.put("password", password)
        values.put("isAdmin", 0)
        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addOrder(login: String, amount: String) {

        val currentDate = LocalDate.now() // Получаем текущую дату
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy") // Задаем формат
        val myDate = currentDate.format(formatter) // Форматируем дату

        val values = ContentValues()
        values.put("login", login)
        values.put("sum", amount)
        values.put("date", myDate)
        val db = this.writableDatabase
        db.insert("orders", null, values)
        db.close()
    }

    fun delItem(name : String) {
        val db = this.writableDatabase
        db.delete("items", "name = ?", arrayOf(name))
    }

    fun isCreated(login: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login'", null)
        return result.moveToFirst()
    }

    fun getUser(login: String, pass: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' and password = '$pass'", null)
        return result.moveToFirst()
    }

    fun isAdmin(login: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' and isAdmin = 1", null)
        return result.moveToFirst()
    }

    fun getAllItems() {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * from items", null)
        Log.d("tag1", result.count.toString())
    }


    @SuppressLint("Range")
    fun getItems() : ArrayList<items> {
        getAllItems()
        val db = this.readableDatabase

        val list = ArrayList<items>()
        val result = db.rawQuery("SELECT * from items", null)
        Log.d("tag1", result.count.toString())
        var stat = result.moveToFirst()
        while (stat) {
            //val id = result.getInt(result.getColumnIndex("ID"))
            val name = result.getString(result.getColumnIndex("name"))
            val description = result.getString(result.getColumnIndex("description"))
            val image = result.getString(result.getColumnIndex("image"))
            val count = result.getInt(result.getColumnIndex("ordersCount"))
            val price = result.getInt(result.getColumnIndex("price"))
            list.add(items(0, name, description, image, count, price))
            stat = result.moveToNext()
        }
        Log.d("tag2", "all good")
        return list

    }

    @SuppressLint("Range")
    fun getTenItems() : ArrayList<items> {
        getAllItems()
        val db = this.readableDatabase

        val list = ArrayList<items>()
        val result = db.rawQuery("SELECT * from items LIMIT 10", null)
        Log.d("tag1", result.count.toString())
        var stat = result.moveToFirst()
        while (stat) {
            //val id = result.getInt(result.getColumnIndex("ID"))
            val name = result.getString(result.getColumnIndex("name"))
            val description = result.getString(result.getColumnIndex("description"))
            val image = result.getString(result.getColumnIndex("image"))
            val count = result.getInt(result.getColumnIndex("ordersCount"))
            val price = result.getInt(result.getColumnIndex("price"))
            list.add(items(0, name, description, image, count, price))
            stat = result.moveToNext()
        }
        Log.d("tag2", "all good")
        return list

    }

    @SuppressLint("Range")
    fun getOrders(login: String) : ArrayList<orders> {
        getAllItems()
        val db = this.readableDatabase

        val list = ArrayList<orders>()
        val result = db.rawQuery("SELECT * from orders where login = '$login'", null)
        Log.d("tag1", result.count.toString())
        var stat = result.moveToFirst()
        while (stat) {
            //val id = result.getInt(result.getColumnIndex("ID"))
            val login = result.getString(result.getColumnIndex("login"))
            val amount = result.getString(result.getColumnIndex("sum"))
            val date = result.getString(result.getColumnIndex("date"))
            list.add(orders(login, amount, date))
            stat = result.moveToNext()
        }
        Log.d("tag2", "all good")
        return list

    }

    public fun reloadTables() {
        val db = this.readableDatabase
        db!!.execSQL("DROP TABLE IF EXISTS users")
        db!!.execSQL("DROP TABLE IF EXISTS items")
        db!!.execSQL("DROP TABLE IF EXISTS orders")
        //onCreate(db)
        var query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, password TEXT, isAdmin INT)"
        db!!.execSQL(query, )
        query = "CREATE TABLE items (id INT PRIMARY KEY, name TEXT, description TEXT, image TEXT, price INT, ordersCount INT)"
        db!!.execSQL(query, )
        query = "CREATE TABLE orders (id INT PRIMARY KEY, login TEXT, name TEXT, sum TEXT, date TEXT)"
        db!!.execSQL(query, )

        val values = ContentValues()
        values.put("login", "admin")
        values.put("password", "admin")
        values.put("isAdmin", 1)
        db.insert("users", null, values)
        db.close()

        val db2 = this.writableDatabase
        val values2 = ContentValues()
        values2.put("name", "testItem")
        values2.put("description", "testItem")
        values2.put("image", "item1")
        values2.put("price", 1000)
        values2.put("ordersCount", 10)
        db2.insert("items", null, values2)
        //db2.close()

        values2.clear()
        //values2.put("id", 0)
        values2.put("name", "Черная пайта М")
        values2.put("description", "testItem")
        values2.put("image", "item1")
        values2.put("price", 5000)
        values2.put("ordersCount", 1)
        db2.insert("items", null, values2)

        values2.clear()
        //values2.put("id", 0)
        values2.put("name", "Белая пайта М")
        values2.put("description", "testItem")
        values2.put("image", "item2")
        values2.put("price", 5500)
        values2.put("ordersCount", 1)
        db2.insert("items", null, values2)

        values2.clear()
        //values2.put("id", 0)
        values2.put("name", "Белая пайта 2 Ж")
        values2.put("description", "testItem")
        values2.put("image", "item3")
        values2.put("price", 6000)
        values2.put("ordersCount", 1)
        db2.insert("items", null, values2)

        values2.clear()
        //values2.put("id", 0)
        values2.put("name", "Белая пайта 2 М")
        values2.put("description", "testItem")
        values2.put("image", "item8")
        values2.put("price", 6500)
        values2.put("ordersCount", 1)
        db2.insert("items", null, values2)

        values2.clear()
        //values2.put("id", 0)
        values2.put("name", "Белая пайта 3 М")
        values2.put("description", "testItem")
        values2.put("image", "item9")
        values2.put("price", 5000)
        values2.put("ordersCount", 1)
        db2.insert("items", null, values2)

        db2.close()

    }

}