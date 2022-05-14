package com.schurkenhuber.traveltracker.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(val id: Int, val name: String)
