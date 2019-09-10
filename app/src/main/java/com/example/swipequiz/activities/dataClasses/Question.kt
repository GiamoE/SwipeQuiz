package com.example.swipequiz.activities.dataClasses

import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

data class Question (
    var question: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS_TEXT = arrayOf(
            "A val and var are the same",
            "Giamo likes the development classes",
            "Football is fun",
            "Giamo enjoys the process report",
            "1 + 1 equals 2"
        )

        val QUESTION_ANSWERS = arrayOf(
            FALSE,
            TRUE,
            TRUE,
            FALSE,
            TRUE
        )
    }
}