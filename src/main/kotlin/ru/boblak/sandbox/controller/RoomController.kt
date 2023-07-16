package ru.boblak.sandbox.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.boblak.sandbox.model.dto.CreateRoomDto
import ru.boblak.sandbox.model.dto.EditRoomDto
import ru.boblak.sandbox.model.dto.RoomDto
import ru.boblak.sandbox.service.RoomService

@Tag(name = "API для взаимодействия с комнатами")
@RestController
@RequestMapping("api/v1/room")
class RoomController(
    private val roomService: RoomService
) {

    @PostMapping("/create")
    fun createRoom(
        @RequestBody dto: CreateRoomDto
    ): ResponseEntity<RoomDto> {
        return ResponseEntity.ok(
            roomService.createRoom(dto)
        )
    }

    @PostMapping("/deactivate/{id}")
    fun deactivateRoom(
        @PathVariable id: String
    ): ResponseEntity<String> {
        roomService.deactivateRoom(id)
        return ResponseEntity.ok(
            "Комната деактивирована"
        )
    }

    @PostMapping("/generate-message/{id}")
    fun generateMessage(
        @PathVariable id: String
    ): ResponseEntity<RoomDto> {
        return ResponseEntity.ok(
            roomService.generateMessage(id)
        )
    }

    @PatchMapping("/edit")
    fun editRoom(
        @RequestBody dto: EditRoomDto
    ): ResponseEntity<RoomDto> {
        return ResponseEntity.ok(
            roomService.editRoom(dto)
        )
    }

    @Operation(
        method = "GET",
        summary = "Получение комнаты по id"
    )
    @GetMapping("/{id}")
    fun getRoomById(
        @PathVariable id: String
    ): ResponseEntity<RoomDto> {
        return ResponseEntity.ok(
            roomService.getRoom(id)
        )
    }
}
