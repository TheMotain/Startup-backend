package fr.iagl.gamification.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.apache.log4j.Logger;

import fr.iagl.gamification.model.TeacherModel;

public class CryptPassword {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(CryptPassword.class);
	
	public static String encryptPassword(TeacherModel teacher) {
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        String toEncript = teacher.getPassword() + teacher.getEmail();
	        crypt.update(toEncript.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        LOG.warn("erreur encriptage mdp");
	        return teacher.getPassword();
	    } 
	    return sha1;
	}

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
