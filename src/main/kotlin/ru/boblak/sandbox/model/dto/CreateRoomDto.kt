package ru.boblak.sandbox.model.dto

data class CreateRoomDto(
    val id: String,
    val words: List<String>,
    val destinationEmployeeName: String
)
