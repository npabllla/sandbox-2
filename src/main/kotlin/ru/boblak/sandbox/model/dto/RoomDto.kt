package ru.boblak.sandbox.model.dto

data class RoomDto(
    val id: String,
    val words: List<String>,
    val generatedMessage: String,
    val destinationEmployeeName: String
)
