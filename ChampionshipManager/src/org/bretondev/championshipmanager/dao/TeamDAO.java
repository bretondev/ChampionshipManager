package org.bretondev.championshipmanager.dao;

import java.util.List;

import org.bretondev.championshipmanager.entities.Team;

public interface TeamDAO {

	public List<Team> listTeams();

	void createTeam(Team t);

	public void deleteTeam(Integer id);

	Team loadTeam(Integer id);

	void updateTeam(Team t);
	
}