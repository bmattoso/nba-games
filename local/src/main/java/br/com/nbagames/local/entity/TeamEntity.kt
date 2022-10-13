package br.com.nbagames.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val nickname: String,
    val color: Int?,
    val logo: String?,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "is_franchise") val isFranchise: Boolean
)
