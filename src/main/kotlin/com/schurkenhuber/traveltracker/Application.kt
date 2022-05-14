package com.schurkenhuber.traveltracker

import com.schurkenhuber.traveltracker.repository.DatabaseFactory
import io.ktor.server.application.*
import com.schurkenhuber.traveltracker.plugins.*
import com.schurkenhuber.traveltracker.repository.CountryRepository
import com.schurkenhuber.traveltracker.repository.TestingDatabasePopulation
import com.schurkenhuber.traveltracker.service.CountryService
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.initialise(
        environment.config.property("ktor.database.driverClassName").getString(),
        environment.config.property("ktor.database.connectionString").getString()
    )
    /*
    val populateDatabase = environment.config.property("ktor.database.populateDatabase").getString().toBooleanStrict()
    if (populateDatabase) {
        val countryRepository: CountryRepository by inject()
        val testingDatabasePopulation = TestingDatabasePopulation(countryRepository)
        runBlocking {
            testingDatabasePopulation.createSampleCountries()
        }
    }
    */

    install(Koin) {
        modules(travelTrackerModule)
    }

    configureRouting()
    configureSecurity()
    configureSerialization()
}
