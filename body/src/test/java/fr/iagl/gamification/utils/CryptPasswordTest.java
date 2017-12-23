package fr.iagl.gamification.utils;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.mockito.Mockito;

import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.UserModel;

public class CryptPasswordTest {

	@Test
	public void testEncriptPassword() throws GamificationServiceException {
		UserModel user = Mockito.mock(UserModel.class);
		Mockito.doReturn("pass").when(user).getPassword();
		Mockito.doReturn("email@gmail.com").when(user).getEmail();
		String output = CryptPassword.encryptPassword(user);
		assertNotEquals("pass", output);
	}
}
