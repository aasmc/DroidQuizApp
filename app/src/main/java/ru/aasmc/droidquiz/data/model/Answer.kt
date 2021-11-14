package ru.aasmc.droidquiz.data.model

import androidx.room.*

/**
 * Model for an answer class that has a one to many relationship with the Question class,
 * i.e one Question can have many Answers.
 * If a question is deleted from the DB, then all answers associated with it are also
 * deleted from DB. (onDelete = CASCADE).
 * Other options could be:
 *  NO_ACTION
 *  RESTRICT (if a parent entity has one or more records mapped to in in the child entity,
 *      then the app is prohibited from deleting the record.
 *  SET_DEFAULT sets a default value to the child entity
 *  SET_NULL sets child to null
 *
 * To avoid full table scan when parent is updated, we add index on the child table.
 */

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(
            entity = Answer::class,
            parentColumns = ["question_id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("question_id")]
)
data class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val answerId: Int,
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name = "is_correct")
    val isCorrect: Boolean,
    val text: String
)
