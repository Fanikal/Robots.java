package robots.txt;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * This class creates two lists, whitelist and blacklist.
 * It opens the robots.txt file of the given site 
 * and parses the robots.txt file.
 * Finally it saves the allowing and disallowing urls of the site to the whitelist and blacklist respectively 
 * @author plaisio
 * @version 4.6.1
*/
class TestVol3_new {
	public ArrayList<String> whitelist =new ArrayList<String> ();
	public  ArrayList<String> blacklist = new ArrayList<String>();
	
	 /**
	  * This method connects us with the robots.txt file of a given site.
	  * Subsequently it parses the file
	  * @param site 
	  * @exception IOException Input error 
	  */
	
	public void Connection (String site) {
		 try {
			 URL url = new URL(site);
			 String url1 = url.getHost();  
			 URL url2 = new URL("https://" +url1+"/robots.txt");
			 
			 

			 URLConnection url2Connection = url2.openConnection();
			 
			 BufferedReader in = new BufferedReader(new InputStreamReader(url2Connection.getInputStream()));
			 
			 String eachLine;
			 /**
			  * The variable input is boolean.
			  * It is initialized as false so that it checks for a user agent with our crawler's name first.
			  * Only if it doesn't exist yet does it check the "User agent: *" field. 
			  */
			 boolean input =false;
			 while(((eachLine = in.readLine()) != null) && (input ==false)) {
				 		 
				 
				 if(eachLine.contains("User-agent: Cynefin")) {
					 eachLine = in.readLine();
					 input =true;
					 while(eachLine.startsWith("Allow:")||(eachLine.startsWith("Disallow: "))) {
						 if (eachLine.startsWith("Allow:")) {
							 String substr = eachLine.substring(6, eachLine.length()).trim();
							 whitelist.add(substr);
							 eachLine=in.readLine();
						 }else  if(eachLine.startsWith("Disallow: ")) {
							 String substr1 = eachLine.substring(9, eachLine.length()).trim();
							 blacklist.add(substr1);
							 eachLine=in.readLine();
						 }
				 	 }
				 }	
			
					 
				 if ((eachLine.startsWith("User-agent: *")) && (input==false)) {
					 eachLine = in.readLine();
					 input = true;
					 do { 
						 if (eachLine.startsWith("Allow:")) {
							String substr = eachLine.substring(6, eachLine.length()).trim();
							 whitelist.add(substr);
						 }else if(eachLine.startsWith("Disallow: ")) {
							 String substr1 = eachLine.substring(9, eachLine.length()).trim();
							 blacklist.add(substr1);
						 }
						 eachLine =in.readLine();
					 }while(eachLine !=null);
				 }
				 
			 }
			 
		 	 }catch(IOException e) {
	         e.printStackTrace();
				 
			 }
	 
		
	 }	  
}

