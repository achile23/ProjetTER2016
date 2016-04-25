package com.terweb.efactor.beans;

import java.io.File;
import java.io.IOException;

import org.rosuda.JRI.Rengine;

import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

/**
 * 
 * @author Ikbal and Mounir
 * @WEB PROJECT 2016 
 * v1.0
 *
 */
public abstract class ClassAbstractCalculEfactor {
	
	/**
	 * 
	 * @param userCurrentID String 
	 * @return path file 
	 * @throws IOException  
	 */
	public String preCalculEfactor(String userCurrentID,String pathCh) throws IOException, ClassCalculException{
		System.out.println("appppelllllllllllllllll");
		File preCalculFile  = new File(pathCh+"DirectoryUsers/"+userCurrentID+"/preCalcul"+userCurrentID+".csv");
		
		if(preCalculFile.exists()){
			   return pathCh+"DirectoryUsers/"+userCurrentID+"/preCalcul"+userCurrentID+".csv";
		}else {
			   Rengine re = ConnectionRClass.getConnection();
			   
		       if (!re.waitForR()) { 
		             throw new ClassCalculException(); 
		       } 
		       else {
		       String convertinteric =pathCh+"ModuleRCalculEfactor/convertinterconfiance.R";
			   
			   String filecsv = pathCh+"DirectoryUsers/"+userCurrentID+"/CalculeR.csv";
			   
		       String fileOutResult =pathCh+"DirectoryUsers/"+userCurrentID+"/preCalcul"+userCurrentID+".csv";
		       
		       String fileR = pathCh+"ModuleRCalculEfactor/preCalculR.R";
		      
			   re.eval("source('"+fileR+"')");
			   re.eval(String.format("convertinteric <- '%s'",convertinteric));
		       re.eval(String.format("fileOutResult <- '%s'",fileOutResult));
		       re.eval(String.format("filecsv <- '%s'",filecsv));
		       
			   re.eval("s<-CalculInterGYGR()");
			   
			   if(!new File(fileOutResult).exists()){
				   return null;
			   }
			   
			   return fileOutResult;	 
		       }      
		}
	}
	
	public abstract void bestExpEfactor() throws ClassCalculException, ClassFileProblemException  ;
	public abstract String allVarDocEfactor() throws ClassCalculException, ClassFileProblemException  ;
	public abstract void allVarTopicEfactor() throws ClassCalculException, ClassFileProblemException ;

}
