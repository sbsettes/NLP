package baseline;

import java.util.ArrayList;
import java.util.HashMap;

public class FindMethods 
{
	
	public static HashMap<String,Integer> findMethods(ArrayList<String> allLines)
	{
		//HashMap
		HashMap<String,Integer> methodsCount = new HashMap();
		//String 
		String method = "Method_Citation";
		String implication = "Implication_Citation";
		String aim ="Aim_Citation";
		String result = "Result_Citation";
		String results = "Results_Citation";
		String hyperthesis = "Hypothesis_Citation";
		
		//Integer
		int countAim =0;
		int countHyperthesis = 0;
		int countResult = 0;
		int countMethod =0;
		int countImplication =0;
		
		for(int i = 0; i<allLines.size();i++)
		{
			String line = allLines.get(i);
			
			if(line.contains(method))
			{
				countMethod++;
			}
			if(line.contains(implication))
			{
				countImplication++;
			}
			if(line.contains(aim))
			{
				countAim++;
			}
			if(line.contains(hyperthesis))
			{
				countHyperthesis++;
			}
			if(line.contains(results)|| line.contains(result))
			{
				countResult++;
			}
		}
		
		methodsCount.put("Method",countMethod);
		methodsCount.put("Aim", countAim);
		methodsCount.put("Hyperthesis", countHyperthesis);
		methodsCount.put("Result", countResult);
		methodsCount.put("Implication", countImplication);
		return methodsCount;
	}

}
