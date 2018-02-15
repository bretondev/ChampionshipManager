package org.bretondev.championshipmanager.dao.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.ChampionshipDAO;
import org.bretondev.championshipmanager.entities.Championship;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ChampionshipDAOImpl implements ChampionshipDAO{

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Championship> listChampionships() {
        Session session = sessionFactory.getCurrentSession();
        List<Championship> list = session.createCriteria(Championship.class).list();
        return list;
    }
    
    @Override
    public void createChampionship(Championship c) {
    	Session session = sessionFactory.getCurrentSession();
        session.save(c);
    }
    
    @Override
    public void deleteChampionship(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("DELETE FROM Championship WHERE id = :id").setInteger("id", id);
        q.executeUpdate();
    }
    
    @Override
    public Championship loadChampionship(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Championship c = (Championship) session.get(Championship.class, id);
        return c;
    }
    
    @Override
    public void updateChampionship(Championship c) {
    	Session session = sessionFactory.getCurrentSession();
        session.update(c);
    }
}


