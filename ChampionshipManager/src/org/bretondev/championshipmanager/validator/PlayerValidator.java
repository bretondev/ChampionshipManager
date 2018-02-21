package org.bretondev.championshipmanager.validator;

import org.bretondev.championshipmanager.entities.Player;
import org.bretondev.championshipmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {

	private PlayerService playerService;
	
	@Autowired(required = true)
	@Qualifier(value = "playerService")
	public void setPlayerService(PlayerService ps) {
	    this.playerService = ps;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Player.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Player p = (Player) target;
		if (p.getFirstName().isEmpty() || p.getFirstName().length() > 255)
			errors.rejectValue("firstName", "errorName", "Le prénom doit faire entre 1 et 255 caractères.");
		if (p.getLastName().isEmpty() || p.getLastName().length() > 255)
			errors.rejectValue("lastName", "errorName", "Le nom doit faire entre 1 et 255 caractères.");
		if (!playerService.isNameUnique(p))
			errors.rejectValue("lastName", "errorUnique", "Un joueur comporte déjà ce nom");
		if (p.getAge() <= 0)
			errors.rejectValue("age", "errorAge", "L'âge doit être un entier positif");
	}
	
}
