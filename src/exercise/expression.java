package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import DS.LinkStack;



public class expression {
	public static boolean isOperators(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }   
	
	private static Map<String,Integer> optPrityMap = new HashMap<String,Integer>(); 
	static
	{
		optPrityMap.put("+", 1);
		optPrityMap.put("-", 1);
		optPrityMap.put("*", 2);
		optPrityMap.put("/", 2);
		optPrityMap.put("(", 0);
		
	}
	static int id = 1;
	
	public static String reversePolish(String expss)
	{
		String s = new String();//没必要在每个符号后面都添加空格
		String s2 = new String();
		String result = new String();
		String temp = new String();
		LinkStack<String> node = new LinkStack<String>();
		for (int i = 0;i < expss.length();i++)
		{
			s = new Character(expss.charAt(i)).toString();
			if(s.matches("\\("))
			{	result += temp + " ";
			    temp = "";
				node.push(s);
			}
			else if(s.matches("\\)"))
			{
				result += temp + " ";
			    temp = "";
				while(!node.isEmpty())
				{
					s2 = node.pop();
					if(s2.equals("("))
						break;
					result += s2;
				}
						
			}
			else if(s.matches("(\\+|\\*|\\-|\\/)"))
			{   
				result += temp + " ";
			    temp = "";
				
				if(node.isEmpty())
					node.push(s);
				else
				{
				    while(!node.isEmpty())
				    {
				    	s2 = node.pop();
				    	if(optPrityMap.get(s2) <  optPrityMap.get(s))
				    	{
				    		node.push(s2);//还要再放回栈顶
				    		break;
				    	}
				    	result += s2;
				    }
				    node.push(s);
				}
			}
			else 
				temp += s;
		}
		result += temp+" ";
		while(!node.isEmpty())
			result += node.pop();
		return result;
	}
	
	public static String calculateSu(String expss)
     {
    	 String s = new String();
    	 String r = new String();
    	 double b1 = 0,b2 = 0;
    	 LinkStack<String> node = new LinkStack<String>();
    	 double tmp = 0;
    	 for (int i = 0;i < expss.length();i++)
    	 {  
    		 s = new Character(expss.charAt(i)).toString();
    		 if(s.matches("\\+"))
    			 node.push((Double.parseDouble(node.pop()) + Double.parseDouble(node.pop()))+"");
    		 else if(s.matches("\\-"))
    			 node.push((Double.parseDouble(node.pop()) - Double.parseDouble(node.pop()))+"");
    		 else if(s.matches("\\*"))
    			 node.push((Double.parseDouble(node.pop()) * Double.parseDouble(node.pop()))+"");
    		 else if(s.matches("\\/"))
    		 {
    			 b1 = Double.parseDouble(node.pop());
    			 b2 = Double.parseDouble(node.pop());
    			 node.push((b2 / b1)+"");
    		 }
    		 else if(s.matches("\\+"))
    			 node.push((Double.parseDouble(node.pop()) + Double.parseDouble(node.pop()))+"");
    		 
    		 else if(s.matches("\\s") && !r.equals("")){
    			 node.push(r);
    		     r = "";
    		 }
    		 else
    			 r += s;
    	 }
    	 return node.pop();
     }
	public static void main(String[] args)
	{
		String test = new String("2");
//		System.out.println(calculateSu("3.5 5.7 + 5.0 + 9.6 * 4.5 -"));
		System.out.println(calculateSu(reversePolish("(1+3.2)/5+5+4*8")));
//		System.out.println(reversePolish("(1+3.2)/5+5+4*8"));
//		System.out.println(test.matches("\\d"));
	}
}
