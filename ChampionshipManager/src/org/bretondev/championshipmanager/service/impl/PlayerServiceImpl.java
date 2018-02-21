package org.bretondev.championshipmanager.service.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.PlayerDAO;
import org.bretondev.championshipmanager.entities.Player;
import org.bretondev.championshipmanager.service.PlayerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService{

	private PlayerDAO playerDAO;
	 
    public void setPlayerDAO(PlayerDAO customerDAO) {
        this.playerDAO = customerDAO;
    }
 
    @Override
    @Transactional
    @Cacheable(value="dataCache", key="'listPlayers'")
    public List<Player> listPlayers() {
        return this.playerDAO.listPlayers();
    }
 
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listPlayers'")
    public void createPlayer(Player p) {
        this.playerDAO.createPlayer(p);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listPlayers'")
    public void deletePlayer(Integer id) {
        this.playerDAO.deletePlayer(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listPlayers'")
    public void updatePlayer(Player c) {
        this.playerDAO.updatePlayer(c);
    }
    
    @Override
    @Transactional
    public Player loadPlayer(Integer id) {
        return this.playerDAO.loadPlayer(id);
    }
    
    @Override
    @Transactional
    public boolean isNameUnique(Player player) {
    	List<Player> list  = this.playerDAO.listPlayers();
    	boolean unique = true;
    	for (Player p : list) {
    		if (p.getFirstName().equals(player.getFirstName()) && 
    				p.getLastName().equals(player.getLastName()) && 
    				p.getId() != player.getId()) {
    			unique = false;
    			break;
    		}
    	}
    	
    	return unique;
    }
}
