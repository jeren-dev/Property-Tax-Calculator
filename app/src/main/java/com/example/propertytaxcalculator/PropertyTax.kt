package com.example.propertytaxcalculator

data class PropertyTax(
    val ownerName: String,
    val propertyType: String,
    val area: Double,
    val buildingAge: String,
    val occupancy: String,
    val zone: String,
    val taxAmount: Double,
    val createdDate: String
)