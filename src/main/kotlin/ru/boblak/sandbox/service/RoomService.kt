package ru.boblak.sandbox.service

import ru.boblak.sandbox.model.dto.CreateRoomDto
import ru.boblak.sandbox.model.dto.EditRoomDto
import ru.boblak.sandbox.model.dto.RoomDto

interface RoomService {

    fun createRoom(dto: CreateRoomDto): RoomDto

    fun editRoom(dto: EditRoomDto): RoomDto

    fun getRoom(id: String): RoomDto

    fun generateMessage(id: String): RoomDto

    fun deactivateRoom(id: String)
}
