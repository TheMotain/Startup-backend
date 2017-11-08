package fr.iagl.gamification.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class RequestToolsTest {

	@Test
	public void testTransformBindingErrorsToStringList(){
		ObjectError error1 = Mockito.mock(ObjectError.class);
		ObjectError error2 = Mockito.mock(ObjectError.class);
		BindingResult binding = Mockito.mock(BindingResult.class);
		Mockito.when(error1.getDefaultMessage()).thenReturn("msg1");
		Mockito.when(error2.getDefaultMessage()).thenReturn("msg2");
		Mockito.when(binding.getAllErrors()).thenReturn(Arrays.asList(new ObjectError[]{error1,error2}));
		List<String> result = RequestTools.transformBindingErrors(binding);
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains("msg1"));
		Assert.assertTrue(result.contains("msg2"));
	}
}
