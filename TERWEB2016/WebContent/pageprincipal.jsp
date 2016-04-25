	<%@page import="java.util.HashMap"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.Arrays"%>
	<%@ include file="Header.jsp" %>
	
	<%@page import="java.io.File"%>
	<%@page import="com.terweb.efactor.beans.IdentifierUserSession"%>
	 <script type="text/javascript">
	  var userIDEx = sessionStorage.getItem("userID");
	    if(userIDEx == null){
		   jQuery(function ($) {
			  $.ajax({
			           type: 'GET',
			           url: 'UserVerificationID.jsp?userID='+userIDEx,
			           timeout: 3000,
			           success: function (data) {
			             var userID = data;
			             sessionStorage.setItem("userID",userID);
			           }
			         });
			}); 
	  }
	</script> 
	    <%
	      ArrayList<String> biomass =(ArrayList<String>) request.getAttribute("biomass");
	      HashMap<String, ArrayList<String>> topics =(HashMap<String, ArrayList<String>>) request.getAttribute("topics");
	      HashMap<String, ArrayList<String>> relations =(HashMap<String, ArrayList<String>>) request.getAttribute("relations");
          
		%>
		
		
		
		
	<div class="row">
		<div class="col-md-offset-3 col-md-6 col-md-offset-3 ">	
	<h4 style="font-weight: bold; border-radius:3px; text-align: center; color: #000; border: 3px solid #364A81">CALCUL DES INDICATEURS STATISTIQUES</h4>	
	<ul id="progress">
	    <li id="etap1" class="pull-left"> <p style="color:#000; text-align: center;font-weight: bold;">1</p></li>
	    <li id="etap2" class="col-md-0 col-md-offset-5"><p style="color:#000;text-align: center;font-weight: bold;">2</p></li>
	    <li id="etap3" class="pull-right"><p style="color:#000;text-align: center;font-weight: bold;">3</p></li>
	</ul>
	</div>
	</div>	
		
	<section id="phase1">
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		  <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title" style="font-weight: bold;">Séléctionner le Biomass</h3>
		    </div>
	        <div class="panel-body">
	          <div class="form-group">
	             <div class="col-md-12">
			        <select class="form-control input-lg" id="selectbiomass">
			         <% for(String bio : biomass){ %>
			          <option><% out.print(bio); %></option>
			         <% } %>
			        </select>
	             </div>
	          </div>
		    </div>
	     </div>
	  </div>	    
	</div>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		  <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title" style="font-weight: bold;">Paramétrer les Topics</h3>
		    </div>
	        <div class="panel-body">
	          <div class="form-group">
	             <div class="col-md-12">
			        <% int i=0;
			           for(String topic : topics.keySet()){ %>
			            
				          <div class="checkbox" id="<% out.print(topic); %>">
				              <input type="checkbox" value="<% out.print(topic); %>"><b style="color: #000;font-weight: bold;"><% out.print(topic); %></b>
				          </div>
				          <div class="panel panel-default">
							  <div class="panel-body">
							          <% for(String oper : topics.get(topic)){%>
							          
									           <label class="col-md-6" id="<% out.print(topic); %>" style="color: #364A81;font-weight: bold;">
											   <input   type="checkbox" name="<% out.print(oper+""+i); i++; %>" id="<% out.print(oper+""+i); i++; %>" value="<% out.print(oper); %>" ><% out.print(oper); %>
											   </label>
							         
							         <% } %>
			                 </div>
		                  </div> 
		                
			           <% } %> 
			    </div>
	          </div>
		    </div>
	     </div>
	  </div>	    
	</div>
	
	<div class="row">
    <div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
	<button type="submit" id="submit1" class="btn btn-primary col-md-12"><b>Etape suivante <i class="glyphicon glyphicon-arrow-right" style="color:#ffffff;margin-left: 6px"></i></b></button>
	</div>
	</div>
	</section>
	
	<section id="phase2">
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		  <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title" style="font-weight: bold;">Paramétrer les  Relations n-aire</h3>
		    </div>
	        <div class="panel-body">
	          <div class="form-group">
	             <div class="col-md-12">
			        <% for(String relation : relations.keySet()){ %>
				          <div class="checkbox"  id="<% out.print(relation); %>">
				              <input type="checkbox" value="<% out.print(relation); %>"><b style="color: #000;font-weight: bold;"> <% out.print(relation); %></b>
				          </div>
				          <div class="panel panel-default">
							  <div class="panel-body">
					          <% for(String oper : relations.get(relation)){%>
					                   <label class="col-md-4" id="<% out.print(relation); %>" style="color: #364A81;font-weight: bold;">
							                 <input type="checkbox" name="<% out.print(oper+"&"+i); i++; %>" id="<% out.print(oper+"&"+i); i++; %>" value="<% out.print(oper); %>" ><% out.print(oper); %>
									   </label>
					          
					         <% } %>
			              </div>
		                </div> 
			         <% } %> 
			      </div>
	          </div>
		    </div>
	     </div>
	  </div>	    
	</div>
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		<button type="submit" id="submitp2" class="btn btn-primary  col-md-5 "><b><i class="glyphicon glyphicon-arrow-left" style="color:#ffffff;margin-right: 6px"></i>Etape précedente</b></button>
	<button type="submit" id="submit2" class="btn btn-primary col-md-5 col-md-offset-2"><b>Etape suivante <i class="glyphicon glyphicon-arrow-right" style="color:#ffffff;margin-left: 6px"></i></b><i class="fa fa-chevron-right
	"></i></button>
	</div>
	</div>
	</section>
		
	<section id="phase3">
	 <div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		  <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title" style="font-weight: bold;">Séléctionner le type d'evaluation </h3>
		    </div>
	        <div class="panel-body">
	          <div class="form-group">
	             <div class="col-md-12">
			     <select class="form-control input-lg" id="selectExp">
			     <option>Efactor Best Experience</option>
			     <option>Efactor All Variation Document</option>
			     <option>Efactor All Variation Topic</option>
			     </select>
			      </div>
	          </div>
		    </div>
	     </div>
	  </div>	
	</div>
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
			<button type="submit" id="submitp3" class="btn btn-primary  col-md-5 "><b><i class="glyphicon glyphicon-arrow-left" style="color:#ffffff;margin-right: 6px"></i>Etape précedente</b></button>
			<button type="submit" id="submit3"   class="btn btn-primary col-md-5 col-md-offset-2" onclick="envoieDonnees()"><b><i class="glyphicon glyphicon-cog" style="color:#ffffff;margin-right: 6px"></i>Lancer le Calcul</b><i class="fa fa-chevron-right
			"></i></button>
	     </div>
	</div>
	
	<div class="row">
		<div class="col-md-offset-4 col-md-4 col-md-offset-4" id="waiting">
			<h6  style="font-weight: bold;"  class="col-md-offset-3 col-md-6 col-md-offset-3">Traitement en cours ...</h6>
			<img alt="waiting" src="Images/wheel.gif" class="col-md-offset-5 col-md-2 col-md-offset5--">
	    </div>
	</div>
	<br>
	<div class="row">
	  <div class="col-md-offset-2 col-md-8 col-md-offset-2" id="erreurproduite">
		<div class="alert alert-dismissible alert-danger">
   			<button type="button" class="close" data-dismiss="alert">&times;</button>
   			<strong>Oh!</strong> Une erreur s'est produite lors du calcul. veuillez réessayer autre fois .
   		</div>
	 </div>
    </div>
    <div class="row">
	  <div class="col-md-offset-2 col-md-8 col-md-offset-2" id="calculincomplet">
   		 <div class="alert alert-dismissible alert-warning">
  			<button type="button" class="close" data-dismiss="alert">&times;</button>
  			<h4 id="infocomplete">Le calcul ne peut se terminer.</h4>
  			<div id="result">
  			</div>
		</div>
	</div>
  </div>
	<br>
	<br>
	  	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-md-offset-2 ">
		  <div class="panel panel-default">
		    <div class="panel-body">
	           <div class="col-md-12">
		           <div id="garphic" style="border: 3px solid gray;">
		           
		           
		           </div>
				      
			   </div>
	          </div>
		    </div>
	     </div>
	  </div>    
	
	</section>
	
	<br>
 <script type="text/javascript">

      $("#submit1").click(function(){
    	  var valeurs = [];
 		 
		  $('input:checked').each(function() {
			   if($(this).parents("div").attr('id')==$(this).val()){
				   valeurs.push($(this).val());
			   }
		  });
		  
		  console.log(valeurs);
			$('input:checked').each(function() {
						  
						  for(var i=0;i<valeurs.length;i++){
							  
							  if(($(this).parent().attr('id')== valeurs[i])&&($(this).val()!=valeurs[i])){
								 
								if($(this).parents("section").attr('id')=="phase1"){
									if(topics[$(this).parent().attr('id')]==null){
										tempt = [];
									}
									tempt.push($(this).val());
									topics[valeurs[i]]=tempt;
									
								}else if($(this).parents("section").attr('id')=="phase2"){
									if(relations[$(this).parent().attr('id')]==null){
										tempr = [];
									}
									tempr.push($(this).val());
									relations[valeurs[i]]=tempr;
								}
							  }
						  }
						   
					  });
      });
	  function envoieDonnees(){
		  
		  $('#submit3').prop('disabled', true);
		  $('#submitp3').prop('disabled', true);
		  $('#waiting').show();
		  
		  var ParametreClass = new Object();
		  
		  var topics = {};	
		  var relations = {};	
		  
		  var valeurs = [];
		 
		  $('input:checked').each(function() {
			   if($(this).parents("div").attr('id')==$(this).val()){
				   valeurs.push($(this).val());
			   }
		  });
		  
		  var tempt = [];
		  var tempr = [];
		  
		  $('input:checked').each(function() {
			  
			  for(var i=0;i<valeurs.length;i++){
				  
				  if(($(this).parent().attr('id')== valeurs[i])&&($(this).val()!=valeurs[i])){
					 
					if($(this).parents("section").attr('id')=="phase1"){
						if(topics[$(this).parent().attr('id')]==null){
							tempt = [];
						}
						tempt.push($(this).val());
						topics[valeurs[i]]=tempt;
						
					}else if($(this).parents("section").attr('id')=="phase2"){
						if(relations[$(this).parent().attr('id')]==null){
							tempr = [];
						}
						tempr.push($(this).val());
						relations[valeurs[i]]=tempr;
					}
				  }
			  }
			   
		  });
			ParametreClass.userID = sessionStorage.getItem("userID").trim();
			ParametreClass.biomass = $('#selectbiomass').val().trim();
			ParametreClass.experience = $('#selectExp').val().trim();
			
			ParametreClass.topics = topics; 
			ParametreClass.relations = relations;
		  
		  $.ajax({
		        url: "http://localhost:5355/TERWEB2016/index",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(ParametreClass),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		 
		        success: function (data) {
		        	
		        	console.log("data est "+data);
		        	
		        	if(data=="Success"){
		        		
		        		   $('#submit3').prop('disabled', false);
		        		   $('#submitp3').prop('disabled', false);
		        		   $('#waiting').hide();	
		        		  
		        	}else if(data=="Failed"){
		        		   console.log("mounir is  "+data);
		        		   $('#erreurproduite').show();
		        		   $('#submit3').prop('disabled', false);
			        	   $('#submitp3').prop('disabled', false);
			        	   $('#waiting').hide();
		  
		        	}else{
		        		   console.log("data is  "+data);
		        		   $('#calculincomplet').show();
		        		   var p_result = $("#result");
		        		   
		        		   $.each(data, function (key, value) {
		        			   
		        			    var span = $("<span style=\"color:#000000 ; font-size:14px\" />");
			                        span.text(value);
			                        p_result.append(span);
			                        p_result.append("<br>");
			               });
		        		    
		        		   
		        		   
		        		 
		        		   $('#submit3').prop('disabled', false);
		        		   $('#submitp3').prop('disabled', false);
		        		   $('#waiting').hide();
	  
		        	}
		        }
		  });
	
		}
	   
	   $('#calculincomplet').hide();
       $('#erreurproduite').hide();
	   $('#waiting').hide();
	   $('#etap1').css("background","#2196f3");
	   $('#etap1').css("border-radius","46px");
       $('#phase2').hide();
       $('#phase3').hide();
       
	   jQuery(function ($) {
		   $('#submit1').click(function () {
			   
			   $('#phase1').hide();
		       $('#phase3').hide();
		       $('#phase2').show();
		       $('#etap2').css("background","#2196f3");
		       $('#etap2').css("border-radius","46px");
		      
		   });
		   
		   $('#submit2').click(function () {
			   $('#phase1').hide();
		       $('#phase2').hide();
		       $('#phase3').show();
		       $('#etap3').css("background","#2196f3");
		       $('#etap3').css("border-radius","46px");
		      
		       
		   });
		   $('#submitp3').click(function () {
			  
			   $('#phase1').hide();
			   $('#phase3').hide();
		       $('#phase2').show();
		       $('#etap3').css("background","#ffffff");
		       $('#etap3').css("border-radius","46px");
		      
		       
		   });
		   $('#submitp2').click(function () {
			   $('#phase2').hide();
			   $('#phase3').hide();
		       $('#phase1').show();
		       $('#etap2').css("background","#ffffff");
		       $('#etap2').css("border-radius","46px");
		      
		       
		   });
		   
		}); 
  
  </script> 
	
	<%@ include file="Footer.jsp" %>