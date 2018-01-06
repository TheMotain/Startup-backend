package fr.iagl.gamification.utils;

import static org.junit.Assert.assertNotEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.mockito.Mockito;

import fr.iagl.gamification.model.UserModel;

public class CryptPasswordServiceTest {

	@Test
	public void testEncriptPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		CryptPasswordService cryptPassword = new CryptPasswordService();
		UserModel user = Mockito.mock(UserModel.class);
		Mockito.doReturn("pass").when(user).getPassword();
		Mockito.doReturn("email@gmail.com").when(user).getEmail();
		String output = cryptPassword.encryptPassword(user);
		assertNotEquals("pass", output);
	}
}
