package com.terweb.efactor.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.rosuda.JRI.Rengine;

import com.opencsv.CSVWriter;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;



public class BestExperienceClass extends ClassAbstractCalculEfactor {

	private String pathCh ;
	private String userCurrentID;
	private BufferedReader  in;
	
	/**
	 * 
	 * @param pathCommun
	 * @param userCurrentID
	 */
	public BestExperienceClass(String pathCommun,String userCurrentID){
		this.pathCh = pathCommun;
		this.userCurrentID = userCurrentID;
	}
	@Override
	public void bestExpEfactor() throws ClassCalculException, ClassFileProblemException {
		
		try {
			
		     String chm = super.preCalculEfactor(userCurrentID , pathCh);
		     
		     if(chm!=null){
		    
				 LinkedList<String> lineFile = new LinkedList<String>();
				 LinkedList<String> lineTemp = new LinkedList<String>();
				 in = new BufferedReader(new FileReader(chm));
				 
				 String sfile = pathCh+"DirectoryUsers/"+userCurrentID+"/fileOutBestEx"+userCurrentID+".csv";
		         
				 CSVWriter writer = new CSVWriter(new FileWriter(sfile));
				 
				 String line;
				 line =  in.readLine() ;
				 StringTokenizer tokenh = new StringTokenizer(line,",\"");
				 while (tokenh.hasMoreTokens()){
	        		 String cellValue = tokenh.nextToken().toString();
	        		 lineFile.add(cellValue);
	        	 }
				
				 String[] arrayh = lineFile.toArray(new String[] {});
				 writer.writeNext(arrayh);
		         lineFile.clear();
		          
		         while (((line =  in.readLine()) != null)){
		                 StringTokenizer token = new StringTokenizer(line,",\"");
		            	 while (token.hasMoreTokens()){
		            		 String cellValue = token.nextToken().toString().trim();
		            		 if(lineFile.size()<=12){
		            			  lineFile.add(cellValue);
		            		 }else {
		            			  lineTemp.add(cellValue);
		            		 }
		            	 }if((lineTemp.size() == 13) && (lineFile.size() == 13)){
		            	   if(lineFile.get(1).toLowerCase().equals(lineTemp.get(1).toLowerCase())){
		            		 if(Integer.valueOf(lineFile.get(2)) == Integer.valueOf(lineTemp.get(2))){
		                       if(Double.valueOf(lineFile.get(8)) > Double.valueOf(lineTemp.get(8))){
		                    			 lineTemp.clear();
		                       }
		                       else{
		                    		     lineFile.clear();
		                    			 lineFile.addAll(lineTemp);
		                    			 lineTemp.clear();
		                       }
		                    		 
		                     }else {
		                    	 lineFile.removeFirst();
		                    	 String[] array = lineFile.toArray(new String[] {});
		                    	 writer.writeNext(array);
		                    	 lineFile.clear();
		                    	 lineFile.addAll(lineTemp);
		                    	 lineTemp.clear();
		                     }
		            		 
		                  }else{
		                	     lineFile.removeFirst();
			                	 String[] array = lineFile.toArray(new String[] {});
			                 	 writer.writeNext(array);
		                    	 lineFile.clear(); 
		                    	 lineFile.addAll(lineTemp);
		                    	 lineTemp.clear();
		                    	 
		                  }
		               }
		            }
		         lineFile.removeFirst();
		         String[] array = lineFile.toArray(new String[] {});
	        	 writer.writeNext(array);
		         writer.close();
		         
		        
		         if(new File(sfile).exists()){
			         boolean bool;
			         bool= CalculEfactor(sfile); 
			         if(bool==false){
			        	 CalculEfactor(sfile);
			         }
		         }else{
		        	 bestExpEfactor();
		         }
			 
	    	 
		     }else {
		    	  throw new ClassFileProblemException();
		     }
		} catch (IOException e) {
			e.printStackTrace();
		}
     }

	public boolean CalculEfactor(String pathFile) throws ClassCalculException{
		 
		   String fileR = pathCh+"ModuleRCalculEfactor/bestCalculEfactor.R";
		   String fileFunc = pathCh+"ModuleRCalculEfactor/efactorFunction.R";
		   String fileOutResult =pathCh+"DirectoryUsers/"+userCurrentID+"/fileBestExResultGraphic"+userCurrentID+".csv";

		   Rengine re = ConnectionRClass.getConnection();
		  
	       if (!re.waitForR()) { 
	    	   throw new ClassCalculException(); 
	       }else {
	       
			   re.eval("source('"+fileR+"')");
			   re.eval(String.format("fileFunc <- '%s'",fileFunc));
		       re.eval(String.format("fileOutResult <- '%s'",fileOutResult));
		       re.eval(String.format("filecsv <- '%s'",pathFile));
		    
		       re.eval("s<-CalculInterEfactor()");
	      
	       }
	       if(!new File(fileOutResult).exists()){
	    	   return false;
	       }
	       
	   return true;
		
	}
	public String allVarDocEfactor() {
		return null;}
    public void allVarTopicEfactor() {}

}
