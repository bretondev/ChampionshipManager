package org.bretondev.championshipmanager.dao;

import java.util.List;

import org.bretondev.championshipmanager.entities.Player;

public interface PlayerDAO {

	public List<Player> listPlayers();

	void createPlayer(Player p);

	public void deletePlayer(Integer id);

	Player loadPlayer(Integer id);

	void updatePlayer(Player p);
	
}