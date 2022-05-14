package com.schurkenhuber.traveltracker.service.dao

import com.schurkenhuber.traveltracker.dto.CountryDto
import com.schurkenhuber.traveltracker.model.Country
import com.schurkenhuber.traveltracker.repository.CountryRepository
import com.schurkenhuber.traveltracker.service.CountryService

class CountryServiceDao(private val countryRepository: CountryRepository) : CountryService {
    override suspend fun findAllCountries(): List<CountryDto> =
        this.countryRepository
            .findAllCountries()
            .map(::createFromModel)

    override suspend fun findCountryByID(id: Int): CountryDto? =
        this.countryRepository
            .findCountryByID(id)
            ?.let(::createFromModel)

    override suspend fun findCountryByName(name: String): CountryDto? =
        this.countryRepository
            .findCountryByName(name)
            ?.let(::createFromModel)

    override suspend fun createCountry(name: String): CountryDto? =
        this.countryRepository
            .createCountry(name)
            ?.let(::createFromModel)
}

private fun createFromModel(country: Country) = CountryDto(
    country.id,
    country.name
)
