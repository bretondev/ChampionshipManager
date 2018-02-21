package org.bretondev.championshipmanager.dao.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.TeamDAO;
import org.bretondev.championshipmanager.entities.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOImpl implements TeamDAO{

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Team> listTeams() {
        Session session = sessionFactory.getCurrentSession();
        List<Team> list = session.createCriteria(Team.class).list();
        return list;
    }
    
    @Override
    public void createTeam(Team t) {
    	Session session = sessionFactory.getCurrentSession();
        session.save(t);
    }
    
    @Override
    public void deleteTeam(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("DELETE FROM Team WHERE id = :id").setInteger("id", id);
        q.executeUpdate();
    }
    
    @Override
    public Team loadTeam(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Team t = (Team) session.get(Team.class, id);
        return t;
    }
    
    @Override
    public void updateTeam(Team t) {
    	Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }
}


