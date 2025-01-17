package com.example.shopapp.Models

class PopularModel {
    private var image : Int? = null
    private var itemName : String = ""
    private var price : Int = 0
    private var count : Int = 1
    private var description : String = ""

    constructor()
    constructor(image : Int?, itemName : String, price : Int, description : String, count : Int = 1) {
        this.image = image
        this.itemName = itemName
        this.price = price
        this.count = 1
        this.description = description
    }

    fun getCount() : Int {
        return count
    }
    fun getImage() : Int? {
        return image
    }

    fun getName() : String {
        return itemName
    }

    fun getPrice() : Int {
        return price
    }

    fun getDescription() : String {
        return description
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun setImage(image: Int?) {
        this.image = image
    }

    fun setItemName(itemName: String) {
        this.itemName = itemName
    }

    fun setPrice(price: Int) {
        this.price = price
    }

    fun setDescription(description: String) {
        this.description = description
    }
}