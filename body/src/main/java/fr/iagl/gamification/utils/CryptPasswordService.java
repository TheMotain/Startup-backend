package fr.iagl.gamification.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.springframework.stereotype.Service;

import fr.iagl.gamification.model.UserModel;

/**
 * Cryptage
 *
 * @author Hélène MEYER
 *
 */
@Service
public class CryptPasswordService {
	
	/**
	 * Cryptage du mot de passe d'un utilisateur
	 * 
	 * @param user l'utilisateur
	 * @return le mot de passe encrypté de l'utilisateur
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public String encryptPassword(UserModel user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	    String sha1 = "";
	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        String toEncript = user.getPassword() + user.getEmail();
        crypt.update(toEncript.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
	    return sha1;
	}

	/**
	 * Transforme byte to hexadécimal
	 * 
	 * @param hash tableau de byte
	 * @return la chaine de caractère transformée
	 */
	private static String byteToHex(final byte[] hash) {
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
