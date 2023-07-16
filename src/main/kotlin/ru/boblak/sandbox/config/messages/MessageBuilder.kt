package ru.boblak.sandbox.config.messages

import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class MessageBuilder(
    private val messageSource: MessageSource
) {
    fun getMessage(code: String?): String = messageSource.getMessage(
        code ?: MessageConstants.COMMON_UNEXPECTED_ERROR,
        null,
        Locale("ru")
    )
}
