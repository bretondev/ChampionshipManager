package org.bretondev.championshipmanager.controller;

import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {
	
	private ChampionshipService championshipService;
	
	@Autowired(required = true)
	@Qualifier(value = "championshipService")
	public void setChampionshipService(ChampionshipService cs) {
	    this.championshipService = cs;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String welcome(Model model) {
	    model.addAttribute("championships", this.championshipService.listChampionships());
	    return "/championship/list";
	}
	

}