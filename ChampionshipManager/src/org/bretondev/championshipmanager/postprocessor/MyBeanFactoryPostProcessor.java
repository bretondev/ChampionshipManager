package org.bretondev.championshipmanager.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
		
		for (String s : clbf.getBeanDefinitionNames())
			System.out.println(s);
		
		BeanDefinition bd = clbf.getBeanDefinition("championshipController");
		if (bd.isSingleton())
			System.out.println("championshipController est bien un Singleton");
	}

}
