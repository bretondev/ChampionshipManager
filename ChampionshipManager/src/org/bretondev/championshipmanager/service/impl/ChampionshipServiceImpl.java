package org.bretondev.championshipmanager.service.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.ChampionshipDAO;
import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChampionshipServiceImpl implements ChampionshipService{

	private ChampionshipDAO championshipDAO;
	 
    public void setChampionshipDAO(ChampionshipDAO customerDAO) {
        this.championshipDAO = customerDAO;
    }
 
    @Override
    @Transactional
    public List<Championship> listChampionships() {
        return this.championshipDAO.listChampionships();
    }
 
    @Override
    @Transactional
    public void createChampionship(Championship c) {
        this.championshipDAO.createChampionship(c);
    }
    
    @Override
    @Transactional
    public void deleteChampionship(Integer id) {
        this.championshipDAO.deleteChampionship(id);
    }
    
    @Override
    @Transactional
    public void updateChampionship(Championship c) {
        this.championshipDAO.updateChampionship(c);
    }
    
    @Override
    @Transactional
    public Championship loadChampionship(Integer id) {
        return this.championshipDAO.loadChampionship(id);
    }
}
