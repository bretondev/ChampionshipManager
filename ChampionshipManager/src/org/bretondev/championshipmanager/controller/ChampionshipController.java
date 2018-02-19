package org.bretondev.championshipmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.bretondev.championshipmanager.entities.Championship;
import org.bretondev.championshipmanager.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/championship")
@SessionAttributes("championships")
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

	@ExceptionHandler
	public String exceptionHandler(Exception e) {
		return "error";
	}
	
	@ModelAttribute(name="championships")
	public List<Championship> getChampionships() {
		return this.championshipService.listChampionships();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String listChampionships(Model model) {
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
	public ModelAndView createChampionship(@ModelAttribute("championship") Championship championship) {
				
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (championship.getName().isEmpty() || championship.getName().length() > 255)
			modelData.put("errorName", "Le nom doit faire entre 1 et 255 caractères.");
		if (!championshipService.isNameUnique(championship.getName()))
			modelData.put("errorUnique", "Une compétiton comporte déjà ce nom");
		
		if (modelData.size() > 0) {
			modelData.put("championship", championship);
			return new ModelAndView("/championship/create", modelData);
		} else {
			this.championshipService.createChampionship(championship);
		    return new ModelAndView("redirect:/championship/");
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteChampionship(@RequestParam("id") Integer id, @RequestParam Map<String,String> params, SessionStatus ss) {
		this.championshipService.deleteChampionship(Integer.valueOf(params.get("id")));
		ss.setComplete();
	    return "redirect:/championship/";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updateChampionship(@ModelAttribute("championship") Championship championship) {
		
		Map<String,Object> modelData = new HashMap<String, Object>();
		if (championship.getName().isEmpty() || championship.getName().length() > 255)
			modelData.put("errorName", "Le nom doit faire entre 1 et 255 caractères.");
		if (!championshipService.isNameUnique(championship.getName()))
			modelData.put("errorUnique", "Une compétition comporte déjà ce nom");
		
		if (modelData.size() > 0) {
			modelData.put("championship", championship);
			return new ModelAndView("/championship/update", modelData);
		} else {
			this.championshipService.updateChampionship(championship);
		    return new ModelAndView("redirect:/championship/");
		}
	}
	
	@RequestMapping(value="/openUpdate", method = RequestMethod.POST)
	public String openUpdateChampionship(@RequestParam("id") Integer id, Model model) {
		Championship c = this.championshipService.loadChampionship(id);
		model.addAttribute("championship", c);
	    return "/championship/update";
	}



}