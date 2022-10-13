package br.com.nbagames.local.team

import br.com.nbagames.local.entity.TeamEntity
import br.com.nbagames.model.Team

class TeamEntityMapper {

    fun mapEntityToModel(teamEntity: TeamEntity): Team = Team(
        id = teamEntity.id,
        name = teamEntity.name,
        nickname = teamEntity.nickname,
        logo = teamEntity.logo,
        isFavorite = teamEntity.isFavorite,
        isFranchise = teamEntity.isFranchise,
        color = teamEntity.color
    )

    fun mapModelToEntity(team: Team): TeamEntity = TeamEntity(
        id = team.id,
        name = team.name,
        nickname = team.nickname,
        logo = team.logo,
        isFavorite = team.isFavorite,
        isFranchise = team.isFranchise ?: false,
        color = team.color
    )
}
