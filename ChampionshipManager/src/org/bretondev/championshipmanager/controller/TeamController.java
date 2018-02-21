package org.bretondev.championshipmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bretondev.championshipmanager.entities.Team;
import org.bretondev.championshipmanager.service.TeamService;
import org.bretondev.championshipmanager.validator.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/team")
@SessionAttributes("teams")
public class TeamController {

	private TeamService teamService;
	
	@Autowired(required = true)
	@Qualifier(value = "teamService")
	public void setTeamService(TeamService cs) {
	    this.teamService = cs;
	}

	private TeamValidator teamValidator;
	
	@Autowired(required = true)
	@Qualifier(value = "teamValidator")
	public void setTeamValidator(TeamValidator cv) {
	    this.teamValidator = cv;
	}
	
	@ExceptionHandler
	public String exceptionHandler(Exception e) {
		return "error";
	}
	
	@ModelAttribute(name="teams")
	public List<Team> getTeams() {
		return this.teamService.listTeams();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String listTeams(Model model) {
	    return "/team/list";
	}
	
	@RequestMapping(value="/openCreate", method = RequestMethod.GET)
	public ModelAndView openCreationTeam(Model model) {
	    return new ModelAndView("/team/create", "team", new Team());
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView createTeam(@ModelAttribute("team") Team team, BindingResult br, SessionStatus ss) {
		
		teamValidator.validate(team, br);
		
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (br.hasErrors()) {
			modelData.put("team", team);
			return new ModelAndView("/team/create", modelData);
		} else {
			this.teamService.createTeam(team);
			ss.setComplete();
		    return new ModelAndView("redirect:/team/");
		}
		
	}
	
	@RequestMapping(value="/openUpdate", method = RequestMethod.POST)
	public String openUpdateTeam(@RequestParam("id") Integer id, Model model) {
		Team t = this.teamService.loadTeam(id);
		model.addAttribute("team", t);
	    return "/team/update";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updateTeam(@ModelAttribute("team") Team team, BindingResult br, SessionStatus ss) {
		
		teamValidator.validate(team, br);
		
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (br.hasErrors()) {
			modelData.put("team", team);
			return new ModelAndView("/team/update", modelData);
		} else {
			this.teamService.updateTeam(team);
			ss.setComplete();
		    return new ModelAndView("redirect:/team/");
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteTeam(@RequestParam("id") Integer id, @RequestParam Map<String,String> params, SessionStatus ss) {
		this.teamService.deleteTeam(Integer.valueOf(params.get("id")));
		ss.setComplete();
	    return "redirect:/team/";
	}
	
}