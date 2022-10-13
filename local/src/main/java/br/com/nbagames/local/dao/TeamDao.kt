package br.com.nbagames.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.nbagames.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(teamEntity: TeamEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<TeamEntity>): List<Long>

    @Query("UPDATE team SET is_favorite = (:isFavorite) WHERE id = (:teamId)")
    suspend fun toggleFavoriteTeam(teamId: Int, isFavorite: Boolean): Int

    @Query("SELECT * FROM team WHERE id IS (:teamId)")
    fun getTeamByIdStream(teamId: Int): Flow<TeamEntity?>

    @Query("SELECT * FROM team WHERE id IS (:teamId)")
    suspend fun getTeamById(teamId: Int): TeamEntity?

    @Query("SELECT * FROM team")
    fun getAllTeams(): Flow<List<TeamEntity>>
}
