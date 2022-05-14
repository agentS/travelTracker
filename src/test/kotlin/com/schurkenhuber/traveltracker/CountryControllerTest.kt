package com.schurkenhuber.traveltracker

import com.schurkenhuber.traveltracker.dto.CountryDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.client.request.*
import kotlin.test.*

private const val UNSPECIFIED_ID = (-1)

class CountryControllerTest {
    suspend fun testCreateCountry(client: HttpClient): CountryDto {
        val countryName = "Kugelmugel"
        val response = client.post("/country") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(CountryDto(name = countryName, id = UNSPECIFIED_ID))
        }
        assertEquals(HttpStatusCode.Created, response.status)
        val responseBody = response.body<CountryDto>()
        assertNotEquals(UNSPECIFIED_ID, responseBody.id)
        assertTrue { responseBody.id >= 0 }
        assertEquals(countryName, responseBody.name)

        return responseBody
    }

    suspend fun testFindCountryByID(lookedUpCountry: CountryDto, client: HttpClient) {
        val getResponse = client.get("/country/${lookedUpCountry.id}")
        assertEquals(HttpStatusCode.OK, getResponse.status)

        val getResponseBody = getResponse.body<CountryDto>()
        assertEquals(lookedUpCountry.id, getResponseBody.id)
        assertEquals(lookedUpCountry.name, getResponseBody.name)
    }

    suspend fun testFindCountryByName(lookedUpCountry: CountryDto, client: HttpClient) {
        val getResponse = client.get("/country/name/${lookedUpCountry.name}")
        assertEquals(HttpStatusCode.OK, getResponse.status)

        val getResponseBody = getResponse.body<CountryDto>()
        assertEquals(lookedUpCountry.id, getResponseBody.id)
        assertEquals(lookedUpCountry.name, getResponseBody.name)
    }

    suspend fun testFindAllCountries(client: HttpClient) {
        val getResponse = client.get("/countries")
        assertEquals(HttpStatusCode.OK, getResponse.status)

        val countries = getResponse.body<List<CountryDto>>()
        assertNotNull(countries)
        assertTrue(countries.isNotEmpty())
    }
}