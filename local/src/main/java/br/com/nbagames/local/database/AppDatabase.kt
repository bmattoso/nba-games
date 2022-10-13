package br.com.nbagames.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.nbagames.local.dao.TeamDao
import br.com.nbagames.local.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}
