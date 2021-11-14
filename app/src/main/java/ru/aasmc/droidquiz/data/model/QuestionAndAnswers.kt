package ru.aasmc.droidquiz.data.model

import androidx.room.Embedded
import androidx.room.Relation

class QuestionAndAnswers {
    @Embedded
    var question: Question? = null

    @Relation(
        parentColumn = "question_id",
        entityColumn = "question_id"
    )
    var answers: List<Answer> = ArrayList()
}
