package curso.api.rest;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContexLoad implements ApplicationContextAware {
	
	@Autowired
	private static ApplicationContext applicationContext;
	
	
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	
		ApplicationContexLoad.applicationContext =  applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
}