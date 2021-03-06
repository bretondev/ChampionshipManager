package org.bretondev.championshipmanager.service.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.ChampionshipDAO;
import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChampionshipServiceImpl implements ChampionshipService{

	private ChampionshipDAO championshipDAO;
	 
    public void setChampionshipDAO(ChampionshipDAO customerDAO) {
        this.championshipDAO = customerDAO;
    }
 
	public void init() {
		System.out.println("Initializing CHampionshipServiceImpl");
	}
	
	public void destroy() {
		System.out.println("Destroying CHampionshipServiceImpl");
	}
	
    @Override
    @Transactional
    @Cacheable(value="dataCache", key="'listChampionships'")
    public List<Championship> listChampionships() {
        return this.championshipDAO.listChampionships();
    }
 
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listChampionships'")
    public void createChampionship(Championship c) {
        this.championshipDAO.createChampionship(c);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listChampionships'")
    public void deleteChampionship(Integer id) {
        this.championshipDAO.deleteChampionship(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listChampionships'")
    public void updateChampionship(Championship c) {
        this.championshipDAO.updateChampionship(c);
    }
    
    @Override
    @Transactional
    public Championship loadChampionship(Integer id) {
        return this.championshipDAO.loadChampionship(id);
    }
    
    @Override
    @Transactional
    public boolean isNameUnique(String name) {
    	List<Championship> list  = this.championshipDAO.listChampionships();
    	boolean unique = true;
    	for (Championship c : list) {
    		if (c.getName().equals(name)) {
    			unique = false;
    			break;
    		}
    	}
    	
    	return unique;
    }
}
