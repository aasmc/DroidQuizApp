package ru.aasmc.droidquiz.data.db.migrations

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(
    tableName = "questions",
    fromColumnName = "difficulty",
    toColumnName = "challengeLevel"
)
class Migration3To4 : AutoMigrationSpec