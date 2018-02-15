package org.bretondev.championshipmanager.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Championship")
@Entity
public class Championship {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}		
		
		private String name;
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
	
}
