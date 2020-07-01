package baseline;
import java.io.*;
	import java.util.*;
	
public class FindFiles 
{
//ArrayList
	static ArrayList<String> annotationsfiles = new ArrayList();
	static ArrayList<String> annotationFiles = new ArrayList();
	static ArrayList<String> allAnnotationLines = new ArrayList();
	static ArrayList<String> reference = new ArrayList();
	static ArrayList<String> citances = new ArrayList();
	// String
	static String dirName = "C:\\Users\\Michaela\\Desktop\\Sommersemester2020\\NLP\\Presentation1\\scisumm-corpus\\data\\Training-Set-2018";
//	static String testName ="C:\\Users\\Michaela\\Desktop\\Sommersemester2020\\NLP\\Presentation1\\scisumm-corpus\\data\\Training-Set-2018\\C00-2123";
	static String annotationfile;
	//HashMap
	static HashMap<String,Integer> methodCounts = new HashMap();
	static HashMap<String,String> citancelines = new HashMap();
	public static void main(String[] args) 
	{
		
		findCitances(dirName);
		//printCitances(citancelines);
		
	}
	
	public static ArrayList<String> findAnnotationfile(String directoryName) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) 
	        {      
	        	annotationfile = file.getPath();
	            if (file.isFile()&& annotationfile.contains(".ann")|| annotationfile.contains(".annv3")) 
	            {
	               annotationsfiles.add(annotationfile);
	            } 
	            else if (file.isDirectory()) 
	            {
	                findAnnotationfile(file.getAbsolutePath());
	            }
	        }
	    return annotationsfiles;
	    }

	public static ArrayList<String> readAnnotationFiles(ArrayList<String> annotationfile)
	{
		
			//ArrayList
			ArrayList<String> annotationsXML = new ArrayList();
			ArrayList<String> xmlAnnotations = new ArrayList();
			//HashSet
			HashSet<String> references = new HashSet();
			//String
			String annotationfilename;
			for(int i =0; i<annotationfile.size();i++)
			{
				annotationfilename = annotationfile.get(i);
				xmlAnnotations = readAnnotation(annotationfilename);
				references.addAll(xmlAnnotations);
			}
			annotationsXML.addAll(references);
			return annotationsXML;
			
		}
	public static ArrayList<String> readAnnotation(String annotationFile)
	{
		//ArrayList
		ArrayList<String> xmlAnnotation = new ArrayList();
		//HashSet
		HashSet<String> lines = new HashSet();
		//String
		String referenceText ;
		String method;
		String ending;
		
		
		
		//Integer
		int numberofCitations = 0;
		//File 
		File file1;
		file1 = new File(annotationFile);
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
		    xmlAnnotation.addAll(lines);
			return xmlAnnotation;
		
	}
	
	public static void printMethodCounts(HashMap<String,Integer> allCounts)
	{
		for(String key : allCounts.keySet())
		{
			System.out.println(key+" : "+ allCounts.get(key)  );
		}
		
	}
	public static void printCitances(HashMap<String,String> allCitancesline)
	{
		int i =0;
		for(String key : allCitancesline.keySet())
		{
			System.out.println("Citation"+i+": "+allCitancesline.get(key)+ " : "+key);
			i++;
		}
	}
	public static void printReferences(HashMap<String,String> allReferenceline)
	{/*
		for(String key : allReferenceline.keySet())
		{
			System.out.println(allReferenceline.get(key)+ " : "+key);
		}
	*/
		 Map<String, String> map = new TreeMap<String ,String>(allReferenceline); 
         System.out.println("After Sorting:");
         Set set2 = map.entrySet();
         Iterator iterator2 = set2.iterator();
         while(iterator2.hasNext()) {
              Map.Entry me2 = (Map.Entry)iterator2.next();
               System.out.println(me2.getValue()+" :") ;
              System.out.println(me2.getKey() );
             
         }
	}
	public static void findRightCitation(ArrayList<String> annotationlines,HashMap<String, String> citancelines)
	{
		double countright=0;
		double allCitances = annotationlines.size();
		double allfoundCitances = citancelines.size();
		double recall;
		double precision;
		double fscore;
			for(String key : citancelines.keySet())
			{
				//System.out.println(key);
				key = key.substring(key.indexOf(' ')+1);
				//System.out.println(key);
				for(int i = 0; i<annotationlines.size();i++)
				{
			
					String line = annotationlines.get(i);
					//System.out.println(line);
				if(line.contains(key))
				{
					countright++;
				}
			}
			
		}
			
			System.out.println("AllFoundCitances:"+citancelines.size());
			System.out.println("AllCitances: "+annotationlines.size());
			
		System.out.println(countright);
		recall = countright/allCitances;
		precision =allCitances/allfoundCitances;
		System.out.println("Recall: "+recall);
		System.out.println("Precision: "+precision);
		System.out.println("F1-Score: "+ ((2*precision*recall)/(precision+recall)));
	}
	public static void findCitances(String testName)
	{
		//HashMap
		HashMap<String,String> referencelines = new HashMap();
		//ArrayList
		ArrayList<String> citanceannotations = new ArrayList();
		
		annotationFiles=  findAnnotationfile(dirName);
		allAnnotationLines = readAnnotationFiles(annotationFiles);
		methodCounts =FindMethods.findMethods(allAnnotationLines);
		citanceannotations = FindReferenceAnnotation.findAnnotationlines(allAnnotationLines);
			reference = FindCitation.findReferenceText(testName);
				referencelines = FindCitation.readReferences(reference);
				citances = FindCitation.findCitanceText(testName);
				//System.out.print(reference.size()+" \n");
				citancelines=FindCitation.readCitanceFile(citances);
				findRightCitation(citanceannotations,citancelines);
				//System.out.println(citancelines.size());
			//	CompareCitationandReference.findCitances(citancelines, referencelines);
			//	printReferences(referencelines);
				//printCitances(citancelines);
	}


}


