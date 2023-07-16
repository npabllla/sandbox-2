package ru.boblak.sandbox.model.dto

data class EditRoomDto(
    val id: String,
    val words: List<String>?,
    val destinationEmployeeName: String?
)
