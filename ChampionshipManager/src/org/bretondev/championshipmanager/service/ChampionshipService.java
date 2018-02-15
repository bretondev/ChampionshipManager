package org.bretondev.championshipmanager.service;

import java.util.List;

import org.bretondev.championshipmanager.entities.Championship;

public interface ChampionshipService {

	public List<Championship> listChampionships();

	void createChampionship(Championship c);

	void deleteChampionship(Integer id);

	Championship loadChampionship(Integer id);

	void updateChampionship(Championship c);
	
}
