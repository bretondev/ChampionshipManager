package org.bretondev.championshipmanager.service;

import java.util.List;

import org.bretondev.championshipmanager.entities.Player;

public interface PlayerService {
	
	public List<Player> listPlayers();

	void createPlayer(Player p);

	void deletePlayer(Integer id);

	Player loadPlayer(Integer id);

	void updatePlayer(Player p);

	boolean isNameUnique(Player player);
}
