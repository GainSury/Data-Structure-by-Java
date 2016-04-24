//package exercise;
//
//import java.util.regex.Pattern;//引入正则表达式
//import DS.LinkStack;
//
//public class test {
//	public static boolean isOperators(String str) {    
//		    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
//		    return !pattern.matcher(str).matches();    
//		  }  
//	public static boolean isNumeric(String str){  
//		  for (int i = str.length();--i>=0;){    
//		   if (!Character.isDigit(str.charAt(i))){  
//		    return false;  
//		   }  
//		  }  
//		  return true;  
//		}
//	public static void main(String[] args)
//	{
//		String s = new String();
//		String node = new String("as5asd");
//		LinkStack<Double> stack = new LinkStack<Double>();
//		double tmp = 0;
//		for(int i = 0;i < node.length();i++)
//		{
//			s = new Character(node.charAt(i)).toString();
//			if(isOperators(s))
//				tmp = Double.parseDouble(s);
//			    stack.push(tmp);
//				
//		}
//		
////		System.out.println((d + 3));
////		System.out.println(s.charAt(0));
////		System.out.print(new Character(s.charAt(2)).toString());
////		s = new Character(s.charAt(2)).toString();
////		System.out.println("s isNum: " + isNumeric(s));
//	}
//}
