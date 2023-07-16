package ru.boblak.sandbox.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.boblak.sandbox.model.entity.RoomEntity

interface RoomRepository : JpaRepository<RoomEntity, String>
