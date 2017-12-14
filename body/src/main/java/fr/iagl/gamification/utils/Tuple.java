/**
 * 
 */
package fr.iagl.gamification.utils;

/**
 * Permet de créer un tuple de deux éléments différents
 * @author alex
 *
 */
public class Tuple<A, B> {
	
	/**
	 * Premier élèment du tuple
	 */
	public A valueA;
	
	/**
	 * Deuxième élèment du tuple
	 */
	public B valueB;
	
	/**
	 * Constructeur
	 * @param valueA;
	 * @param valueB;
	 */
	public Tuple(A valueA, B valueB) {
		this.valueA = valueA;
		this.valueB = valueB;
	}
}
