package ru.boblak.sandbox.service

interface OpenAIService {

    fun generateMessage(phrase: String): String
}
