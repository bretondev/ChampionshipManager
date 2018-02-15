package org.bretondev.championshipmanager.dao;

import java.util.List;

import org.bretondev.championshipmanager.entities.Championship;

public interface ChampionshipDAO {

	public List<Championship> listChampionships();

	void createChampionship(Championship c);

	public void deleteChampionship(Integer id);

	Championship loadChampionship(Integer id);

	void updateChampionship(Championship c);
	
}
