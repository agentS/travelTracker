package com.schurkenhuber.traveltracker.controller

import com.schurkenhuber.traveltracker.dto.CountryDto
import com.schurkenhuber.traveltracker.service.CountryService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.countryRouting() {
    val countryService: CountryService by inject()

    route("/country") {
        get {
            this.call.respond(countryService.findAllCountries())
        }
        get("{id?}") {
            val id = (this.call.parameters["id"] ?: return@get this.call.respondText(
                "Missing ID",
                status = HttpStatusCode.BadRequest
            )).toInt()

            val country = countryService.findCountryByID(id)
            if (country != null) {
                this.call.respond(HttpStatusCode.OK, country)
            } else {
                this.call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/name/{name?}") {
            val name = this.call.parameters["name"] ?: return@get this.call.respondText(
                "Missing ID",
                status = HttpStatusCode.BadRequest
            )

            val country = countryService.findCountryByName(name)
            if (country != null) {
                this.call.respond(HttpStatusCode.OK, country)
            } else {
                this.call.respond(HttpStatusCode.NotFound)
            }
        }
        post {
            val country = this.call.receive<CountryDto>()
            val createdCountry = countryService.createCountry(country.name)
            if (createdCountry != null) {
                this.call.respond(HttpStatusCode.Created, createdCountry)
            } else {
                this.call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}
