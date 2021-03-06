package com.terweb.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;


import org.json.simple.JSONObject;

import com.terweb.efactor.beans.AllVarDocClass;
import com.terweb.efactor.beans.AllVarTopicClass;
import com.terweb.efactor.beans.BestExperienceClass;
import com.terweb.efactor.beans.ParametreClass;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;
import com.terweb.packageExceptions.Exception_AbsenceDocument;
import com.terweb.packageExceptions.Exception_AbsenceExperienceBiomass;
import com.terweb.packageExceptions.Exception_BDDException;
import com.terweb.packageExceptions.Exception_FichierCalcule;
import com.terweb.packageExceptions.Exception_MatriceCalculVide;
import com.terweb.packageExceptions.Exception_ParseException;
import com.terweb.packageExceptions.Exception_SparqlConnexion;
import com.terweb.packageInterrogationDonnees.Initialisation;
import com.terweb.packageInterrogationDonnees.Interrogation;
import com.terweb.packageInterrogationDonnees.Object_RapportCalculMatrice;
public class PagePrincipal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
   /**
    * @see HttpServlet#HttpServlet()
    */
    public PagePrincipal() {
       super();
       
       
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		try {
			
			ArrayList<String> biomass = Initialisation.initBiomass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"));
			HashMap<String, ArrayList<String>> topics = Initialisation.initTopicOperation();
			HashMap<String, ArrayList<String>> relations = Initialisation.initRelationParameters(getServletContext().getRealPath("/").replaceAll("\\\\", "/"));
		
		    
			request.setAttribute("biomass",biomass);
			
			request.setAttribute("topics",topics);
			
			request.setAttribute("relations",relations);
			
            if((!biomass.isEmpty())&&(!topics.isEmpty())&&(!relations.isEmpty())){
            	this.getServletContext().getRequestDispatcher( "/pageprincipal.jsp" ).forward( request, response );
            }
			
		
		} catch (Exception_SparqlConnexion  |Exception_BDDException |SQLException e) {
			e.printStackTrace();
		} 
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		    response.setContentType("application/json");  
		
		    String messageErreur = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
	        
	        ObjectMapper mapper = new ObjectMapper();
	        
	        ParametreClass parametres = mapper.readValue(json, ParametreClass.class);
	        
	        Object_RapportCalculMatrice rapport = new Object_RapportCalculMatrice();
	        
	        //try {
	        	
	        	String pathUser=getServletContext().getRealPath("/").replaceAll("\\\\", "/")+"DirectoryUsers/"+parametres.getUserID();
	        
	        	//try {
					
	        		    	        
		        	//rapport = Interrogation.initMatriceCalcul(pathUser,parametres.getBiomass(),parametres.getTopics(),parametres.getRelations());				
	        	
	        	
	        
	        	
	        	//System.out.println("Final :"+rapport.getMessage());

	
	        	/*if(!rapport.getMessage().isEmpty()){
	        		
	        		messageErreur="Failed";
	        		
	        		mapper.writeValue(response.getOutputStream(), rapport.getMessage());
	        		
	        	}*/
	     
	       /* } catch (Exception_AbsenceDocument
					| Exception_AbsenceExperienceBiomass
					| Exception_FichierCalcule | Exception_ParseException
					| Exception_BDDException | SQLException
					| Exception_SparqlConnexion | Exception_MatriceCalculVide e) {
	        	     
	                 messageErreur="Failed";
	                 
	    			 mapper.writeValue(response.getOutputStream(), "Failed");	
	    			 
			
				
			}
	         */
	        /*************** calcul en R ******************/
	        
	         // if(messageErreur.isEmpty()){
	            	
	            
	        	  if(parametres.getExperience().equalsIgnoreCase("Efactor Best Experience")){
	        	
	        	
	        			BestExperienceClass b =  new BestExperienceClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),parametres.getUserID());
	    		
			    		try {
			    			
			    			
			    			b.bestExpEfactor();
			    			
			    			
							JSONObject objetSuccess = new JSONObject();
							
							objetSuccess.put("Success", "fileBestExResultGraphic");
							
		    				mapper.writeValue(response.getOutputStream(),objetSuccess);
		    				
			    				
			    			
			    		} catch (ClassCalculException | ClassFileProblemException e) {
			    			
			    			    messageErreur="Failed";
			    			
			    				JSONObject objetSuccess = new JSONObject();
								
								objetSuccess.put("Failed", "");
			    			    
								mapper.writeValue(response.getOutputStream(),objetSuccess);
			    		}
	    		
	    		
	        }else if(parametres.getExperience().equalsIgnoreCase("Efactor All Variation Document")){
	        	
	        
	        		AllVarDocClass b =  new AllVarDocClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"), "5316b1f2-6159-4cbf-b6b8-97498d15c229");
				
				  
				    	JSONObject objetSuccess = new JSONObject();
				    	
						//b.allVarDocEfactor();
			
						
						objetSuccess.put("Success","fileAllVarDocResultPreGraphic");
						
						mapper.writeValue(response.getOutputStream(),objetSuccess);	
	    				
	    				
	    				
					
			    
			    
	        }else if(parametres.getExperience().equalsIgnoreCase("Efactor All Variation Topic")){
	        	
	        	
		        	AllVarTopicClass b =  new AllVarTopicClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),"5316b1f2-6159-4cbf-b6b8-97498d15c229");
		    		
					try {
						
						b.allVarTopicEfactor();
						
						JSONObject objetSuccess = new JSONObject();
						
						objetSuccess.put("Success", "fileAllVarTopicResultGraphic");
						
	    				mapper.writeValue(response.getOutputStream(),objetSuccess);	
						
					} catch (ClassCalculException | ClassFileProblemException e) {
						
						messageErreur="Failed";
						
						JSONObject objetSuccess = new JSONObject();
						
						objetSuccess.put("Failed", "");
	    			    
						mapper.writeValue(response.getOutputStream(),objetSuccess);	
    			   }
	      }
	        	  
	  }
	}
//}

