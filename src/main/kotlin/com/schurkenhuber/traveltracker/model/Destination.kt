package com.schurkenhuber.traveltracker.model

import org.jetbrains.exposed.sql.Table

data class Destination(val id: Int, val name: String, val latitude: Double, val longitude: Double)

object Destinations : Table() {
    val id = this.integer("id").autoIncrement()
    val name = this.varchar("name", 256)
    val latitude = this.float("latitude")
    val longitude = this.float("longitude")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
