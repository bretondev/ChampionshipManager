package org.bretondev.championshipmanager.main;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.entities.Team;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.stereotype.Component;

@Component
public class SchemaGenerator {

	public static void main(String[] args) {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("dialect", "org.hibernate.dialect.MySQLDialect");
        settings.put("hibernate.connection.url", "jdbc:mysql://poc.cmnkcqvxmzmt.us-east-2.rds.amazonaws.com/ChampionshipManager");
        settings.put("hibernate.connection.username", "admin");
        settings.put("hibernate.connection.password", "dgedev18");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("show_sql", "true");
 
        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build());
        metadata.addAnnotatedClass(Championship.class);
        metadata.addAnnotatedClass(Team.class);
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile("db-schema.sql");
        
        schemaExport.execute(EnumSet.of(TargetType.STDOUT), SchemaExport.Action.CREATE, metadata.buildMetadata());
    }
	
}
