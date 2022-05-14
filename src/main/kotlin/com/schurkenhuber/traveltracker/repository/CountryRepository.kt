package com.schurkenhuber.traveltracker.repository

import com.schurkenhuber.traveltracker.model.Country

interface CountryRepository {
    suspend fun findAllCountries(): List<Country>
    suspend fun findCountryByID(id: Int): Country?
    suspend fun findCountryByName(name: String): Country?
    suspend fun createCountry(name: String): Country?
}