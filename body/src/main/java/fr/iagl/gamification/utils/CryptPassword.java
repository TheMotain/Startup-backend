package fr.iagl.gamification.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;

import org.apache.log4j.Logger;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.UserModel;

/**
 * Cryptage
 *
 * @author Hélène MEYER
 *
 */
public class CryptPassword {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(CryptPassword.class);
	
	/**
	 * Cryptage du mot de passe d'un utilisateur
	 * 
	 * @param user l'utilisateur
	 * @return le mot de passe encrypté de l'utilisateur
	 * @throws GamificationServiceException s'il y a eu un soucis lors du cryptage
	 */
	public static String encryptPassword(UserModel user) throws GamificationServiceException {
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        String toEncript = user.getPassword() + user.getEmail();
	        crypt.update(toEncript.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        LOG.warn("erreur encriptage mdp");
	        throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_CRYPTAGE_PASSWORD));
	    } 
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
