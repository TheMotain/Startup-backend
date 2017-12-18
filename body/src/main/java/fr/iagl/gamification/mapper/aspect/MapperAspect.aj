/**
 * 
 */
package fr.iagl.gamification.mapper.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import fr.iagl.gamification.model.MappingModel;


/**
 * @author alex
 *
 */
@Aspect
public class MapperAspect {
	
	private static final Logger LOGGER = Logger.getLogger(MapperAspect.class);
	
	@Before("execution (void fr.iagl.gamification.model.MappingModel.map(..))")
	public void logBeforeStart(JoinPoint joinPoint) {
		MappingModel mm = ((MappingModel)joinPoint.getTarget());
		mm.setI();
	}
}
