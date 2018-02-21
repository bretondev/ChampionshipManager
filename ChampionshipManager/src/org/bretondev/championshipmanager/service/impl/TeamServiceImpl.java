package org.bretondev.championshipmanager.service.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.TeamDAO;
import org.bretondev.championshipmanager.entities.Team;
import org.bretondev.championshipmanager.service.TeamService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;
	 
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
 
    @Override
    @Transactional
    @Cacheable(value="dataCache", key="'listTeams'")
    public List<Team> listTeams() {
        return this.teamDAO.listTeams();
    }
 
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listTeams'")
    public void createTeam(Team t) {
        this.teamDAO.createTeam(t);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listTeams'")
    public void deleteTeam(Integer id) {
        this.teamDAO.deleteTeam(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value="dataCache", key="'listTeams'")
    public void updateTeam(Team t) {
        this.teamDAO.updateTeam(t);
    }
    
    @Override
    @Transactional
    public Team loadTeam(Integer id) {
        return this.teamDAO.loadTeam(id);
    }
    
    @Override
    @Transactional
    public boolean isNameUnique(String name) {
    	List<Team> list  = this.teamDAO.listTeams();
    	boolean unique = true;
    	for (Team t : list) {
    		if (t.getName().equals(name)) {
    			unique = false;
    			break;
    		}
    	}
    	
    	return unique;
    }
}
