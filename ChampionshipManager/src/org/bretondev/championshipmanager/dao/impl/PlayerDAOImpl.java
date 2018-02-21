package org.bretondev.championshipmanager.dao.impl;

import java.util.List;

import org.bretondev.championshipmanager.dao.PlayerDAO;
import org.bretondev.championshipmanager.entities.Player;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAOImpl implements PlayerDAO{

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Player> listPlayers() {
        Session session = sessionFactory.getCurrentSession();
        List<Player> list = session.createCriteria(Player.class).list();
        return list;
    }
    
    @Override
    public void createPlayer(Player p) {
    	Session session = sessionFactory.getCurrentSession();
        session.save(p);
    }
    
    @Override
    public void deletePlayer(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("DELETE FROM Player WHERE id = :id").setInteger("id", id);
        q.executeUpdate();
    }
    
    @Override
    public Player loadPlayer(Integer id) {
    	Session session = sessionFactory.getCurrentSession();
        Player p = (Player) session.get(Player.class, id);
        return p;
    }
    
    @Override
    public void updatePlayer(Player p) {
    	Session session = sessionFactory.getCurrentSession();
        session.update(p);
    }
}


