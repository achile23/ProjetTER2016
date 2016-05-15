package com.terweb.exception;

public class ClassFileProblemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * M�thode permettant de d�clencher une erreur si 
	 * le fichier contenant le glucose Yield et Rate � 95 de confiance n'est pas cr�e
	 */
	public ClassFileProblemException(){
		System.out.println("un probleme s'est produit , le fichier precalcul n'a �t� pas cr�e ");	
	}

}
