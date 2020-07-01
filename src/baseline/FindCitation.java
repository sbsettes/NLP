package baseline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FindCitation 
{		public static int count = 0;
	public static String referncefile;
	public static HashMap<String,String> references = new HashMap();
	//public static ArrayList<String> referencesfiles = new ArrayList();
	public static HashSet<String> citanceLine = new HashSet();
	public static ArrayList<String> findReferenceText(String referencedir)
	{
		//ArrayList
		ArrayList<String> references = new ArrayList();
		  File directory = new File(referencedir);

		    // Get all files from a directory.
		    File[] fList = directory.listFiles();
		    if(fList != null)
		        for (File file : fList) 
		        {      
		        	referncefile = file.getPath();
		        	
		            if (file.isFile() && referncefile.contains("Reference_XML")&& referncefile.contains(".xml")) 
		            {
		            	//System.out.println(referncefile);
		            	citanceLine.add(referncefile);
		            } 
		            else if (file.isDirectory()) 
		            {
		                findReferenceText(file.getAbsolutePath());
		            }
		        }
		
		   // System.out.println(references.size());
		references.addAll(citanceLine);
		return references;
	}
	public static ArrayList<String> findCitanceText(String referencedir)
	{
		//ArrayList
		ArrayList<String> citances = new ArrayList();
		  File directory = new File(referencedir);

		    // Get all files from a directory.
		    File[] fList = directory.listFiles();
		    if(fList != null)
		        for (File file : fList) 
		        {      
		        	referncefile = file.getPath();
		        	
		            if (file.isFile()&& referncefile.contains("Citance_XML")&& referncefile.contains(".xml")) 
		            { 
		            	
		            	citanceLine.add(referncefile);
		              
		            } 
		            else if (file.isDirectory()) 
		            {
		                findCitanceText(file.getAbsolutePath());
		            }
		        }
		citances.addAll(citanceLine);
		return citances;
	}
	public static HashMap<String,String> readCitanceFile(ArrayList<String> citancesfile)
	{
		
		
		
		
		//ArrayList
		ArrayList<String> citanceXML = new ArrayList();
		ArrayList<String> xmlAnnotations = new ArrayList();
		//HashSet
		
		//String
		String citancefilename;
		String referencename ="";
		//HashMap
		HashMap<String,String> citancelines = new HashMap();
		HashMap<String,String> references = new HashMap();
		HashMap<String,String> citances = new HashMap();
		for(int i =0; i<citancesfile.size();i++)
		{
			
			citancefilename = citancesfile.get(i);
			referencename =  findReferenceName(citancefilename);
			if(referencename==null)
			{
				continue;
			}
			else
			{
			xmlAnnotations = readCitances(citancefilename);
			citancelines = findCitances(xmlAnnotations,referencename);
			references.putAll(citancelines);
			}
		}
		citances.putAll(references);
		return citances;
		
	}
	
public static ArrayList<String> readCitances(String citanceFile)
{
	//ArrayList
	ArrayList<String> xmlCitances = new ArrayList();
	//HashSet
	HashSet<String> lines = new HashSet();
	lines.clear();
	//String
	

	//File 
	File file1;
	file1 = new File(citanceFile);
	BufferedReader in = null;
	if(!file1.canRead() || !file1.isFile())
	{
		System.exit(0);
	}
	try
	{
		in = new BufferedReader(new FileReader(file1));
		String line = null;
		while((line = in.readLine())!= null)
		{
			if(line.isEmpty()==true)
        	{
        		continue;
        	}
        	else
        	{
        		
        	//	System.out.println(line);
				lines.add(line);	
				}
		}
	}
	 catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally
	    {
	        if(in !=null)
	            try
	            {
	                in.close();
	            }
	            catch (Exception e)
	            {

	            }
	    }
	    xmlCitances.addAll(lines);
		return xmlCitances;
	
}

public static HashMap<String,String> readReferences(ArrayList<String> references)
{
	//HashMap
	HashMap<String,String> referenceLines = new HashMap();
	//String
	String line ;
	String referencepaper;
	//ArrayList
	ArrayList<String> referencelines = new ArrayList();
	for(int i =0; i<references.size();i++)
	{
		line = references.get(i); 
		referencepaper = line.substring(line.lastIndexOf('\\')+1);
		//System.out.print(referencepaper);
		referencelines = readCitances(line);
		referenceLines = orderReferences(referencelines,referencepaper);
		
	
	}
	return referenceLines;
}
public static String findReferenceName(String citancefile)
{
	//String
	String referencename =null;
	
	if(citancefile.contains("C00-2123"))
	{
		referencename= "Tillmann and Ney";
	}
	else if(citancefile.contains("C02-1025"))
	{
		referencename = "Chieu";
		
	}
	else if (citancefile.contains("C04-1089"))
	{
		referencename = "Shao and Ng";
		
	}
	else if (citancefile.contains("C08-1098"))
	{
		referencename = "Schmid and Laws";
		
	}else if (citancefile.contains("C10-1045"))
	{
		referencename = "Green and Manning";
		
	}else if (citancefile.contains("C90-2039"))
	{
		referencename = "Kogure";
		
	}else if (citancefile.contains("C98-1097"))
	{
		referencename = "Jobbins and Evett";
		
	}else if (citancefile.contains("C94-2154"))
	{
		referencename = "Gerdemann and  King";
		
	}else if (citancefile.contains("D09-1023"))
	{
		referencename = "Gimpel and Smith";
		
	}else if (citancefile.contains("D10-1058"))
	{
		referencename = "Zhao and Gildea";
		
	}else if (citancefile.contains("D10-1083"))
	{
		referencename = "Lee et al.";
		
	}else if (citancefile.contains("E03-1020"))
	{
		referencename = "Dorow and Widdows";
		
	}else if (citancefile.contains("E09-2008"))
	{
		referencename = "Hulden";
		
	}else if (citancefile.contains("H05-1115"))
	{
		referencename = "Otterbacher et al.";
		
	}else if (citancefile.contains("H89-2014"))
	{
		referencename = "Kupiec";
		
	}else if (citancefile.contains("I05-5011"))
	{
		referencename = "Sekine";
		
	}else if (citancefile.contains("J00-3003"))
	{
		referencename = "Stolcke et al.";
		
	}else if (citancefile.contains("J96-3004"))
	{
		referencename = " Sproat et al";
		
	}else if (citancefile.contains("N01-1011"))
	{
		referencename = "Pedersen";
		
	}else if (citancefile.contains("N04-1038"))
	{
		referencename = "Bean and Riloff";
		
	}else if (citancefile.contains("N06-2049"))
	{
		referencename = "Zhang et al.";
		
	}else if (citancefile.contains("N09-1001"))
	{
		referencename = "Su and Markert";
		
	}else if (citancefile.contains("N09-1025"))
	{
		referencename = "Chiang et al";
		
	}else if (citancefile.contains("P00-1025"))
	{
		referencename = "Beesley and Karttunen";
		
	}else if (citancefile.contains("P05-1004"))
	{
		referencename = " Curran";
		
	}else if (citancefile.contains("P05-1053"))
	{
		referencename = "Zhou et al";
		
	}else if (citancefile.contains("P06-2124"))
	{
		referencename = "Zhao and Xing";
		
	}else if (citancefile.contains("P07-1040"))
	{
		referencename = "Rosti et al";
		
	}else if (citancefile.contains("P98-1046"))
	{
		referencename = "Dang et al";
		
	}else if (citancefile.contains("P98-1081"))
	{
		referencename = "van Halteren et";
		
	}else if (citancefile.contains("P98-2143"))
	{
		referencename = "Mitkov";
		
	}else if (citancefile.contains("W03-0410"))
	{
		referencename = "Stevenson and Joanis";
		
	}else if (citancefile.contains("W04-0213"))
	{
		referencename = "Stede";
		
	}else if (citancefile.contains("W06-3909"))
	{
		referencename = "Pantel and Pennacchiotti";
		
	}else if (citancefile.contains("W08-2222"))
	{
		referencename = "Bos,";
		
	}else if (citancefile.contains("W09-0621"))
	{
		referencename = "Wubben et al";
		
	}else if (citancefile.contains("W11-0815"))
	{
		referencename = "Acosta et al,";
		
	}else if (citancefile.contains("W95-0104"))
	{
		referencename = "Golding";
		
	}else if (citancefile.contains("X96-1048"))
	{
		referencename = "Sundheim";
		
	}
	
	return referencename;
}
public static HashMap<String,String> findCitances(ArrayList<String> citancefiles, String referencefile)
{
	//HashMap
	HashMap<String,String> citancelines = new HashMap();
	//String
	String referencepaper;
	String line;
	String referenceline;
	referencepaper = findReferencePaper(referencefile);
	
	for(int i =0;i<citancefiles.size(); i++)
	{
		line = citancefiles.get(i);
		if(referencefile.isEmpty() == true)
		{
			continue;
		}
		else
		{
		if(line.contains(referencefile))
		{
			//System.out.println(line);
			
			
			citancelines.put(line, referencefile);
		
			}
		
		}
		
	}

	return citancelines;
}
public static String findReferencePaper(String referencefile)
{
	//String
	String referencepaper =" ";

	 if(referencefile.equals("Tillmann and Ney"))
	{
		referencepaper = "C00-2123";
	}
	else if(referencefile.equals("Chieu"))
	{
		referencepaper = "C02-1025";
	}
	else if(referencefile.equals("Shao and Ng"))
	{
		referencepaper = "C04-1089";
	}else if(referencefile.equals("Schmid and Laws"))
	{
		referencepaper = "C08-1098";
	}else if(referencefile.equals("Green and Manning"))
	{
		referencepaper = "C10-1045";
	}else if(referencefile.equals("Kogure"))
	{
		referencepaper = "C90-2039";
	}else if(referencefile.equals("Gerdemann and  King"))
	{
		referencepaper = "C94-2154";
	}else if(referencefile.equals("Jobbins and Evett"))
	{
		referencepaper = "C98-1097";
	}else if(referencefile.equals("Gimpel and Smith"))
	{
		referencepaper = "D09-1023";
	}else if(referencefile.equals("Zhao and Gildea"))
	{
		referencepaper = "D10-1058";
	}else if(referencefile.equals("Lee et al."))
	{
		referencepaper = "D10-1083";
	}else if(referencefile.equals("Dorow and Widdows"))
	{
		referencepaper = "E03-1020";
	}else if(referencefile.equals("Hulden"))
	{
		referencepaper = "E09-2008";
	}else if(referencefile.equals("Otterbacher et al."))
	{
		referencepaper = "H05-1115";
	}else if(referencefile.equals("Kupiec"))
	{
		referencepaper = "H89-2014";
	}else if(referencefile.equals("Sekine"))
	{
		referencepaper = "I05-5011";
	}else if(referencefile.equals("Stolcke et al."))
	{
		referencepaper = "J00-3003";
	}else if(referencefile.equals(" Sproat et al"))
	{
		referencepaper = "J96-3004";
	}else if(referencefile.equals("Pedersen"))
	{
		referencepaper = " N01-1011";
	}else if(referencefile.equals("Bean and Riloff"))
	{
		referencepaper = "N04-1038";
	}else if(referencefile.equals("Zhang et al."))
	{
		referencepaper = "N06-2049";
	}else if(referencefile.equals("Su and Markert"))
	{
		referencepaper = "N09-1001";
	}else if(referencefile.equals("Chiang et al"))
	{
		referencepaper = "N09-1025";
	}else if(referencefile.equals("Beesley and Karttunen"))
	{
		referencepaper = "P00-1025";
	}else if(referencefile.equals("Curran"))
	{
		referencepaper = "P05-1004";
	}else if(referencefile.equals("Zhou et al"))
	{
		referencepaper = "P05-1053";
	}else if(referencefile.equals("Zhao and Xing"))
	{
		referencepaper = "P06-2124";
	}else if(referencefile.equals("Rosti et al"))
	{
		referencepaper = "P07-1040";
	}else if(referencefile.equals("Dang et al"))
	{
		referencepaper = "P98-1046";
	}else if(referencefile.equals("van Halteren et"))
	{
		referencepaper = "P98-1081";
	}else if(referencefile.equals("Mitkov"))
	{
		referencepaper = "P98-2143";
	}else if(referencefile.equals("Stevenson and Joanis"))
	{
		referencepaper = "W03-0410";
	}else if(referencefile.equals("Stede"))
	{
		referencepaper = "W04-0213";
	}else if(referencefile.equals("Pantel and Pennacchiotti"))
	{
		referencepaper = "W06-3909";
	}else if(referencefile.equals("Bos,"))
	{
		referencepaper = "W08-2222";
	}else if(referencefile.equals("Wubben et al"))
	{
		referencepaper = "W09-0621 ";
	}else if(referencefile.equals("Acosta et al,"))
	{
		referencepaper = "W11-0815 ";
	}else if(referencefile.equals("Golding"))
	{
		referencepaper = "W95-0104";
	}
else if(referencefile.equals("Sundheim"))
{
	referencepaper = "X96-1048";
}
	return referencepaper;
}


public static HashMap<String, String> orderReferences(ArrayList<String> referencefiles, String referencepaper)
{
	//HAshMap
	
	String line;
	for(int i = 0; i<referencefiles.size(); i++)
	{
		line = referencefiles.get(i);
		references.put(line, referencepaper);
		
	}
	

	return references;
}
}
