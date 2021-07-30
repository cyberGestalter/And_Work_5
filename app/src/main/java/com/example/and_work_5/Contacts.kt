package com.example.and_work_5

class Contact(var firstName: String, var lastName: String, var phone: String) {

    override fun toString(): String {
        return "$firstName $lastName"
    }

    companion object {
        val CONTACTS = mutableListOf(
            Contact("Clara", "Oswald", "+448105557778934"),
            Contact("Rosa", "Tiler", "+448105657975926"),
            Contact("Donna", "Noble", "+448104437138817"),
            Contact("Amy", "Pond", "+448105719309216")
        )
    }
}