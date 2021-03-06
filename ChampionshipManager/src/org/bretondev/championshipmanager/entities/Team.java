package org.bretondev.championshipmanager.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Team")
@Entity
public class Team implements Serializable {
	
		private static final long serialVersionUID = -1489458958714188787L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}		
		
		@Column(unique=true)
		private String name;
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
	
		@OneToMany(mappedBy = "team")
		private Set<Player> players;
		public Set<Player> getPlayers() {return players;}
		public void setPlayers(Set<Player> players) {this.players = players;}
		
}
