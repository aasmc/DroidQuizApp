package ru.aasmc.droidquiz.data.model

import androidx.room.*

@Entity(
    tableName = "questions",
    indices = [Index("question_id")]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name = "text")
    val text: String,
    val difficulty: Int = 0
)
