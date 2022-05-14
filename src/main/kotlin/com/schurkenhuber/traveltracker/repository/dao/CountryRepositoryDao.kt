package com.schurkenhuber.traveltracker.repository.dao

import com.schurkenhuber.traveltracker.model.Countries
import com.schurkenhuber.traveltracker.model.Country
import com.schurkenhuber.traveltracker.repository.CountryRepository
import com.schurkenhuber.traveltracker.repository.DatabaseFactory
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CountryRepositoryDao : CountryRepository {
    companion object {
        private fun resultRowToCountry(row: ResultRow) = Country(
            id = row[Countries.id],
            name = row[Countries.name],
        )
    }

    override suspend fun findAllCountries(): List<Country> = DatabaseFactory.runQuery {
        Countries.selectAll().map(CountryRepositoryDao::resultRowToCountry)
    }

    override suspend fun findCountryByID(id: Int): Country? = DatabaseFactory.runQuery {
        Countries
            .select { Countries.id eq id }
            .map(CountryRepositoryDao::resultRowToCountry)
            .singleOrNull()
    }

    override suspend fun findCountryByName(name: String): Country? = DatabaseFactory.runQuery {
        Countries
            .select { Countries.name eq name }
            .map(CountryRepositoryDao::resultRowToCountry)
            .singleOrNull()
    }

    override suspend fun createCountry(name: String): Country? = DatabaseFactory.runQuery {
        val insertStatement = Countries.insert {
            it[Countries.name] = name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(CountryRepositoryDao::resultRowToCountry)
    }
}