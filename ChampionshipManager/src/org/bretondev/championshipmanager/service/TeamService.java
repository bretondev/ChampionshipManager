package org.bretondev.championshipmanager.service;

import java.util.List;

import org.bretondev.championshipmanager.entities.Team;


public interface TeamService {

	public List<Team> listTeams();

	void createTeam(Team t);

	void deleteTeam(Integer id);

	Team loadTeam(Integer id);

	void updateTeam(Team t);

	boolean isNameUnique(String name);
	
}
