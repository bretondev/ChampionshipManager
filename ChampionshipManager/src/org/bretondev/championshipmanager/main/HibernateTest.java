package org.bretondev.championshipmanager.main;

import java.util.List;

import org.bretondev.championshipmanager.entities.Championship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateTest {

	
	public static void main(String[] args) {
		listChampionships();
	}
	
	private static void listChampionships() {
		
		SessionFactory  sFactory = HibernateUtil.getSessionFactory();
		Session session = sFactory.getCurrentSession();
		
		Transaction t = session.beginTransaction();
		List<Championship> championships = (List) session.createCriteria(Championship.class).list();
		
		
		for (Championship c : championships)
			System.out.println(c.getName());
		
		t.commit();
		sFactory.close();
		
	}
	
}
