package com.schurkenhuber.traveltracker.repository

import com.schurkenhuber.traveltracker.model.Countries
import com.schurkenhuber.traveltracker.model.Destinations
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun initialise(driverClassName: String, connectionString: String) {
        val database = Database.connect(connectionString, driverClassName)

        transaction(database) {
            SchemaUtils.create(Countries)
            SchemaUtils.create(Destinations)
        }
    }

    suspend fun <T> runQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}