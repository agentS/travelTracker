package com.schurkenhuber.traveltracker.model

import org.jetbrains.exposed.sql.Table

data class Country(val id: Int, val name: String)

object Countries : Table() {
    val id = this.integer("id").autoIncrement()
    val name = this.varchar("name", 256)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
