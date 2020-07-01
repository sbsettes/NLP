package baseline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class CompareCitationandReference
{ 
	public static ArrayList<String> citancelines = new ArrayList();
	public static ArrayList<String> referencelines = new ArrayList();
	public static void findCitances(HashMap<String,String> citances,HashMap<String,String> references)
	{	
		// int 
		int countsameWords =0; 
		int countnumber = 1 ;
		double score = 0;
		//ArrayList
		ArrayList<String> words = new ArrayList();
		ArrayList<String> wordsCitance = new ArrayList();
		//String
		String word;
		String referencename ;
		String ending;
		String referenceSentence;
		//HashMap
		HashMap<String,Double> lines = new HashMap();
		HashMap<String,String> citancesSentences = new HashMap();
	
		for(String citancekey : citances.keySet())
		{
			String citancevalue = citances.get(citancekey);
			if(citancekey.equals(citancevalue))
			{
				continue;
			}
			else
			{
			//System.out.println("Citance: "+citancevalue);
			for(String referencekey : references.keySet())
			{
				lines.clear();
				countsameWords=0;
				String referencesvalue = references.get(referencekey);
			
				ending = referencesvalue.substring(referencesvalue.lastIndexOf(".")+1);
				//System.out.println(referencesvalue);
				referencesvalue = referencesvalue.substring(0,referencesvalue.length()-ending.length()-1);
				
				if(referencesvalue.equals("J98-2005"))
				{
					continue;
				}
				//referencesvalue = referencesvalue.substring(0,referencesvalue.length()-ending.length()-1);
				referencename = FindCitation.findReferenceName(referencesvalue);
			//	System.out.println(referencesvalue);
				if(referencename.equals(citancevalue))
				{
				
				if(referencekey.contains("SECTION")||referencekey.equals("\" number = \"4\">")||referencekey.contains("PAPER")|| referencekey.contains("ABSTRACT"))
				{
					continue;
				}
				else
				{
					
				words =findWords(referencekey);
				wordsCitance = findWords(citancekey);
				for(int i =0; i<words.size();i++)
				{
					word = words.get(i);
					//System.out.println(word);
					if(word.equals("The")||word.equals("in")||word.equals("In")||word.equals("the")||word.contains(".") || word.contains("(")||word.contains(")")||word.length()<3)
					{
						continue;
					}
					else
					{
						word = word+ " ";
					if(citancekey.contains(word))
					{
						countsameWords++;
					}
					}
				}
				//System.out.println(countsameWords);
				if(words.size()== wordsCitance.size())
				{
				
					score = countsameWords;
				
				}
				else
				{
					score = countsameWords*0.5;
				}
				
			}
			lines.put(referencekey,score);
				
				
				referenceSentence = findfittingSentence(lines);
				citancesSentences.put(citancekey, referenceSentence);
			}
				//System.out.println(referenceSentence);
				
			}
			}
			
			}
		
		for(String key: citancesSentences.keySet())
		{
			if(citancesSentences.get(key).equals("-"))
			{
				continue;
			}
			/*
			System.out.println("Citance: "+ countnumber);
			System.out.println("Citance: "+key);
			System.out.println("Reference: "+citancesSentences.get(key));
			
			countnumber++;
			*/
		}
		buildTable(citances,references,citancesSentences);
		
		
	}
	public  static ArrayList<String>  findWords(String line)
	{
		ArrayList<String> words = new ArrayList();
		HashSet<String> wordsinLine = new HashSet();
		String word;
		String lines ;
		//System.out.println(line);
		if(line.contains("</S>"))
		{
		word = line.substring(line.lastIndexOf("</S>")+1);
		line = line.substring(0,line.length()-word.length()-1);
		line = line.substring(line.lastIndexOf(">")+1);
		}
		
	
	        int count = 0;
	        for (int i = 0; i <line.length(); i++)
	        {
	        	if(line.isEmpty()==false)
	        	{
	          
	        	word = line.substring(line.lastIndexOf(' ')+1);
	        	wordsinLine.add(word);
	      		//System.out.println(word);
	      	//	System.out.println(line);
	      		lines =line.substring(0,line.length()-word.length());
	      		if(lines.isEmpty()==true)
	      		{
	      			
	      			
	      			continue;
	      		}
	      			
	      		line = line.substring(0,line.length()-word.length()-1);
	      		
	      		i =0;
	      	//	System.out.println(line);
	      		
	      		
	          
	        }
	        }
	        words.addAll(wordsinLine);
	        return words;

	}
	public static String findfittingSentence(HashMap<String,Double> linescount)
	{
		//String
		String referenceSentence = "-";
		//int
		double count =0.0;
		for(String key : linescount.keySet())
		{
			if(linescount.get(key)>count)
				{
				referenceSentence= key;
				}
		}
		
		
		return referenceSentence;
	}
	public static void buildTable(HashMap<String,String> citation,HashMap<String,String> reference,HashMap<String,String>citationsentences)
	{
		int countCitation = 1;
		for(String citationsentenceskey : citationsentences.keySet()) 
		{
			String citationsentencesvalue = citationsentences.get(citationsentenceskey);
			
			for(String citationkey : citation.keySet())
			{
				String citationvalue = citation.get(citationkey);
				
			for(String referencekey : reference.keySet())
			{
				String referencevalue = reference.get(referencekey);
				
				if(citationkey.equals(citationsentenceskey)&& referencekey.equals(citationsentencesvalue))
				{
					System.out.println("Citationnumber: "+ countCitation);
					System.out.println("Referencefile : "+ referencevalue);
					System.out.println("Referencesentence:" +referencekey);
					System.out.println("Citationsentence : "+ citationkey);
					countCitation++;
					
				}
			}
			}
			
		}
	
}
}