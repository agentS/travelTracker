package com.schurkenhuber.traveltracker.service

import com.schurkenhuber.traveltracker.dto.CountryDto

interface CountryService {
    suspend fun findAllCountries(): List<CountryDto>
    suspend fun findCountryByID(id: Int): CountryDto?
    suspend fun findCountryByName(name: String): CountryDto?
    suspend fun createCountry(name: String): CountryDto?
}
