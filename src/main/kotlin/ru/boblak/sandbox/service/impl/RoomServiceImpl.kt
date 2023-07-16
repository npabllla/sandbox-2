package ru.boblak.sandbox.service.impl

import org.springframework.stereotype.Service
import ru.boblak.sandbox.model.dto.CreateRoomDto
import ru.boblak.sandbox.model.dto.EditRoomDto
import ru.boblak.sandbox.model.dto.RoomDto
import ru.boblak.sandbox.model.entity.RoomEntity
import ru.boblak.sandbox.repository.RoomRepository
import ru.boblak.sandbox.service.OpenAIService
import ru.boblak.sandbox.service.RoomService

@Service
class RoomServiceImpl(
    private val openAIService: OpenAIService,
    private val roomRepository: RoomRepository
) : RoomService {

    override fun createRoom(dto: CreateRoomDto): RoomDto {
        val createdEntity = roomRepository.saveAndFlush(
            RoomEntity(
                id = dto.id,
                words = dto.words,
                destinationEmployeeName = dto.destinationEmployeeName,
                generatedMessage = "",
                active = true
            )
        )

        return RoomDto(
            id = createdEntity.id,
            words = createdEntity.words,
            destinationEmployeeName = createdEntity.destinationEmployeeName,
            generatedMessage = createdEntity.generatedMessage
        )
    }

    override fun deactivateRoom(id: String) {
        val entity = roomRepository.findById(id).orElseThrow {
            IllegalArgumentException("Room with id = $id doesn't exist")
        }
        entity.active = false
        roomRepository.saveAndFlush(entity)
    }

    override fun editRoom(dto: EditRoomDto): RoomDto {
        val entityForEdit = roomRepository.findById(dto.id).orElseThrow {
            IllegalArgumentException("Room with id = ${dto.id} doesn't exist")
        }

        if (!entityForEdit.active) {
            throw IllegalStateException("Room with id = ${dto.id} not active")
        }

        entityForEdit.words = dto.words ?: entityForEdit.words
        entityForEdit.destinationEmployeeName = dto.destinationEmployeeName ?: entityForEdit.destinationEmployeeName

        val editedEntity = roomRepository.saveAndFlush(entityForEdit)

        return RoomDto(
            id = editedEntity.id,
            words = editedEntity.words,
            destinationEmployeeName = editedEntity.destinationEmployeeName,
            generatedMessage = editedEntity.generatedMessage
        )
    }

    override fun getRoom(id: String): RoomDto {
        val entity = roomRepository.findById(id).orElseThrow {
            IllegalArgumentException("Room with id = $id doesn't exist")
        }

        if (!entity.active) {
            throw IllegalStateException("Room with id = $id not active")
        }

        return RoomDto(
            id = entity.id,
            words = entity.words,
            destinationEmployeeName = entity.destinationEmployeeName,
            generatedMessage = entity.generatedMessage
        )
    }

    override fun generateMessage(id: String): RoomDto {
        val entity = roomRepository.findById(id).orElseThrow {
            IllegalArgumentException("Room with id = $id doesn't exist")
        }

        val generationPhrase = """
            Создай поздравление для человека с именем: ${entity.destinationEmployeeName}, 
            используя следующие ключевые слова :  ${entity.words.joinToString(", ")}
        """.trimIndent()

        val generatedMessage = openAIService.generateMessage(generationPhrase)

        return RoomDto(
            id = entity.id,
            words = entity.words,
            destinationEmployeeName = entity.destinationEmployeeName,
            generatedMessage = generatedMessage
        )
    }
}
