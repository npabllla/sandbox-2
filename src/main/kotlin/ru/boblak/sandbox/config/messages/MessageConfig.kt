package ru.boblak.sandbox.config.messages

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
class MessageConfig {

    fun messageSource(): MessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:message")
        setDefaultEncoding("UTF-8")
    }
}
