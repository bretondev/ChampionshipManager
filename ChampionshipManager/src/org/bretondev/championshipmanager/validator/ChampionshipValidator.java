package org.bretondev.championshipmanager.validator;

import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChampionshipValidator implements Validator {

	private ChampionshipService championshipService;
	
	@Autowired(required = true)
	@Qualifier(value = "championshipService")
	public void setChampionshipService(ChampionshipService cs) {
	    this.championshipService = cs;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Championship.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Championship c = (Championship) target;
		if (c.getName().isEmpty() || c.getName().length() > 255)
			errors.rejectValue("name", "errorName", "Le nom doit faire entre 1 et 255 caractères.");
		if (!championshipService.isNameUnique(c.getName()))
			errors.rejectValue("name", "errorUnique", "Une compétiton comporte déjà ce nom");
	}
	
}
