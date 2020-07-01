package baseline;

import java.util.ArrayList;
import java.util.HashSet;

public class FindReferenceAnnotation
{
	public static ArrayList<String> findAnnotationlines(ArrayList<String> annotationlines)
	{
		//ArrayList
		ArrayList<String> citation = new ArrayList();
		//HashSet
		HashSet<String> citancetext = new HashSet();
		//String
		String ending;
		
		for(int i = 0; i<annotationlines.size(); i++)
		{
			String line = annotationlines.get(i);
			ending = line.substring(line.lastIndexOf("Reference Offset:")+1);
			line = line.substring(0,line.length()-ending.length()-1);
			line = line.substring(line.lastIndexOf("Citation Text: ")+15);
			//System.out.println(line);
			citancetext.add(line);
		}
		citation.addAll(citancetext);
		return citation;
	}

}
