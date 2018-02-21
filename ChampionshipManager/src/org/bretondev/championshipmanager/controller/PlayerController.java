package org.bretondev.championshipmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bretondev.championshipmanager.entities.Player;
import org.bretondev.championshipmanager.service.PlayerService;
import org.bretondev.championshipmanager.validator.PlayerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/player")
@SessionAttributes("players")
public class PlayerController {

	private PlayerService playerService;
	
	@Autowired(required = true)
	@Qualifier(value = "playerService")
	public void setPlayerService(PlayerService cs) {
	    this.playerService = cs;
	}

	private PlayerValidator playerValidator;
	
	@Autowired(required = true)
	@Qualifier(value = "playerValidator")
	public void setPlayerValidator(PlayerValidator pv) {
	    this.playerValidator = pv;
	}
	
	@ModelAttribute(name="players")
	public List<Player> getPlayers() {
		return this.playerService.listPlayers();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String listPlayers(Model model) {
	    return "/player/list";
	}
	
	@RequestMapping(value="/openCreate", method = RequestMethod.GET)
	public ModelAndView openCreationPlayer(Model model) {
	    return new ModelAndView("/player/create", "player", new Player());
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView createPlayer(@ModelAttribute("player") Player player, BindingResult br, SessionStatus ss) {
		
		playerValidator.validate(player, br);
		
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (br.hasErrors()) {
			modelData.put("player", player);
			return new ModelAndView("/player/create", modelData);
		} else {
			this.playerService.createPlayer(player);
			ss.setComplete();
		    return new ModelAndView("redirect:/player/");
		}
		
	}
	
	@RequestMapping(value="/openUpdate", method = RequestMethod.POST)
	public String openUpdatePlayer(@RequestParam("id") Integer id, Model model) {
		Player t = this.playerService.loadPlayer(id);
		model.addAttribute("player", t);
	    return "/player/update";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updatePlayer(@ModelAttribute("player") Player player, BindingResult br, SessionStatus ss) {
		
		playerValidator.validate(player, br);
		
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (br.hasErrors()) {
			modelData.put("player", player);
			return new ModelAndView("/player/update", modelData);
		} else {
			this.playerService.updatePlayer(player);
			ss.setComplete();
		    return new ModelAndView("redirect:/player/");
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deletePlayer(@RequestParam("id") Integer id, @RequestParam Map<String,String> params, SessionStatus ss) {
		this.playerService.deletePlayer(Integer.valueOf(params.get("id")));
		ss.setComplete();
	    return "redirect:/player/";
	}
	
}