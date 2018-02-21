package org.bretondev.championshipmanager.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="Player")
@Entity
public class Player implements Serializable {
	
		private static final long serialVersionUID = 4588753329135299123L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}		
		
		private String firstName;
		public String getFirstName() {return firstName;}
		public void setFirstName(String firstName) {this.firstName = firstName;}
		
		private String lastName;
		public String getLastName() {return lastName;}
		public void setLastName(String lastName) {this.lastName = lastName;}
		
		private int age;
		public int getAge() {return age;}
		public void setAge(int age) {this.age = age;}
		
		@ManyToOne(optional=false)
		@JoinColumn(name="team_id")
		private Team team;
		public Team getTeam() {return team;}
		public void setTeam(Team team) {this.team = team;}
		
}
