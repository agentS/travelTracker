package com.schurkenhuber.traveltracker

import com.schurkenhuber.traveltracker.repository.CountryRepository
import com.schurkenhuber.traveltracker.repository.dao.CountryRepositoryDao
import com.schurkenhuber.traveltracker.service.CountryService
import com.schurkenhuber.traveltracker.service.dao.CountryServiceDao
import org.koin.dsl.module

val travelTrackerModule = module {
    single<CountryService> { CountryServiceDao(this.get()) }
    single<CountryRepository> { CountryRepositoryDao() }
}
