package com.schurkenhuber.traveltracker.repository

class TestingDatabasePopulation(private val countryRepository: CountryRepository) {
    suspend fun createSampleCountries() {
        countryRepository.createCountry("Bosnia and Herzegovina")
        countryRepository.createCountry("Montenegro")
    }
}
