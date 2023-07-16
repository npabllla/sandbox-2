package ru.boblak.sandbox.model.entity

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "rooms")
class RoomEntity(
    @Id
    @Column(name = "id")
    var id: String,

    @Column(name = "words")
    @ElementCollection
    var words: List<String>,

    @Column(name = "generated_message")
    var generatedMessage: String,

    @Column(name = "destination_employee_name")
    var destinationEmployeeName: String,

    @Column(name = "active")
    var active: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoomEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return this.javaClass.hashCode()
    }
}
