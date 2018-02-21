package org.bretondev.championshipmanager.validator;

import org.bretondev.championshipmanager.entities.Team;
import org.bretondev.championshipmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeamValidator implements Validator {

	private TeamService teamService;
	
	@Autowired(required = true)
	@Qualifier(value = "teamService")
	public void setTeamService(TeamService cs) {
	    this.teamService = cs;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Team.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Team t = (Team) target;
		if (t.getName().isEmpty() || t.getName().length() > 255)
			errors.rejectValue("name", "errorName", "Le nom doit faire entre 1 et 255 caractères.");
		if (!teamService.isNameUnique(t.getName()))
			errors.rejectValue("name", "errorUnique", "Une équipe comporte déjà ce nom");
	}
	
}
