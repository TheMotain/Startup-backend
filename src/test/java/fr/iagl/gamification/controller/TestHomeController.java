package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

public class TestHomeController {
 
    @InjectMocks
    private HomeController homeController;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testHomeController() throws Exception {
    	ModelAndView view = homeController.index("name");
    	assertEquals("Hello, name!", view.getViewName());
    }

}
