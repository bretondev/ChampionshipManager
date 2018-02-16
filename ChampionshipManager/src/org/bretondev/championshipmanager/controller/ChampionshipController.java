package org.bretondev.championshipmanager.controller;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/championship")
public class ChampionshipController implements ServletContextAware, ServletConfigAware{

	private ServletContext servletContext;
	private ServletConfig servletConfig;
	
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	private ChampionshipService championshipService;
	
	@Autowired(required = true)
	@Qualifier(value = "championshipService")
	public void setChampionshipService(ChampionshipService cs) {
	    this.championshipService = cs;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listChampionships(@ModelAttribute ArrayList<Championship> championships, Model model) {
	    model.addAttribute("championships", this.championshipService.listChampionships());
	    
	    //Temporaire - sert à voir comment fonctionne ServletContext et ServletConfig
	    System.out.println(servletContext.getContextPath());
	    System.out.println(servletConfig.getServletName());
	    
	    
	    return "/championship/list";
	}
	
	@RequestMapping(value="/openCreate", method = RequestMethod.GET)
	public ModelAndView openCreationChampionship(Model model) {
	    return new ModelAndView("/championship/create", "championship", new Championship());
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createChampionship(@ModelAttribute("championship") Championship championship, Model model) {
		this.championshipService.createChampionship(championship);
		model.addAttribute("championships", this.championshipService.listChampionships());
	    return "/championship/list";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteChampionship(@RequestParam("id") Integer id, Model model) {
		this.championshipService.deleteChampionship(id);
		model.addAttribute("championships", this.championshipService.listChampionships());
	    return "/championship/list";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateChampionship(@ModelAttribute("championship") Championship championship, Model model) {
		this.championshipService.updateChampionship(championship);
		model.addAttribute("championships", this.championshipService.listChampionships());
	    return "/championship/list";
	}
	
	@RequestMapping(value="/openUpdate", method = RequestMethod.POST)
	public String openUpdateChampionship(@RequestParam("id") Integer id, Model model) {
		Championship c = this.championshipService.loadChampionship(id);
		model.addAttribute("championship", c);
	    return "/championship/update";
	}



}