package com.schurkenhuber.traveltracker

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun runIntegrationTests() = testApplication {
        environment {
            config = ApplicationConfig("application_test.conf")
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        this@ApplicationTest.testCountryController(client)
    }

    private suspend fun testCountryController(client: HttpClient) {
        val countryControllerTest = CountryControllerTest()
        val country = countryControllerTest.testCreateCountry(client)
        countryControllerTest.testFindCountryByID(country, client)
        countryControllerTest.testFindCountryByName(country, client)
    }
}