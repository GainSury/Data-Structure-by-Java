package exercise;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.IOException;

import DS.LinkList;
import DS.HashMap;

class Info
{
	public String gender;
	public String phoneNumber;        
	public String email;
	Info(String gender_,String phoneNumber_,String email_)
	{
		gender = gender_;
		phoneNumber = phoneNumber_;
		email = email_;
	}
	
}

class ContactList
{
	private HashMap<String,Info> NameList;
    private LinkList<String> names = new LinkList<String>();//name sets
	
	
	public ContactList(int num)
	{
		 NameList = new HashMap<String,Info>(num);
	}
	
	//put remove search(key)
	public void add(String key,String gender,String phoneNumber,String email)
	{
	   addi(key,new Info(gender,phoneNumber,email));
	   names.add(key);
	}

	private void addi(String key,Info value)
	{
	   NameList.add(key, value);	
	}
	
	public Info remove(String key)
	{
		return NameList.remove(key);
	}
	
	public Info get(String key)
	{
		return NameList.get(key);
	}
	
	public String toString()
	{
		String s = new String();
		s += "姓名------性别-------电话-----------电子邮件\n";
		for(String name:names)
		{
			s += name+"   |  ";
			s += get(name).gender + "           | " + get(name).phoneNumber + " |  " +get(name).email + "\n";
			
		}
	    return s;
		
	}
	
	
}

public class AddressBook{
    //data
	private static ContactList list = new ContactList(5000);
		
	
	
	//wait for user inputting
	private static String readU() throws IOException {
        InputStreamReader is_reader = new InputStreamReader(System.in);
        return new BufferedReader(is_reader).readLine();
    }
	private HashMap<Integer,String> hm = new HashMap<Integer,String>(89);
	private static void showMainMenu()
	{
		System.out.println("Desktop__________________________________");
		System.out.println("    1. Find                              |");
		System.out.println("    2. Add                               |");
		System.out.println("    3. ShowAll                           |");
		System.out.println("    4. Save                              |");
		System.out.println("    5. Exit                              |");
	}
	
	private static void showFindMenu()
	{
		System.out.println("Desktop>Find______________________________");
		System.out.println("    1. Enter the name                    |");
		System.out.println("    2. return                            |");
	}
	
	private static String readFindChoice()
	{   
		String choice = new String();
		try
	    {
			do{
		    	System.out.print("please enter your choice(using Numbers): ");        
		    	choice = readU();
//				System.out.print("\n\n"+choice+"\n\n"); for debug
//				System.out.print("\n\n"+isChoiceValid(choice)+""+"\n\n"); //for debug
		    }while(!isChoiceValid(choice,2));
	    }catch(IOException e)
    	{
    		e.printStackTrace();
    	}
		return choice;
	}
	
	
	
	private static Info findPerson()
	{
		String name = new String();
		Info result;
		System.out.print("Enter the name: ");
		try
		{
			name = readU();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		result = list.get(name);
		if(result == null)
			System.out.println("cannot find this person!");
		else
		{
			System.out.println(name + "|" + 
	 	                       result.gender + "|" + 
				               result.phoneNumber+ "|" + 
				               result.email+ "|");
			while(true)
			{
				showFindSubMenu(name);
				String choiceFindSub = readFindSubChoice();
				if(choiceFindSub.equals("1"))
				{
					list.remove(name);
					System.out.println("already delete "+name);
					break;
				}	
				else
					break;
			}
			
			
		}
		return result;
	}
	
	private static void showFindSubMenu(String name)
	{
		System.out.println("Desktop>Find>"+name+"________________________");
		System.out.println("   1. Delete                              |");
		System.out.println("   2. return                              | ");
		
	}
	
	private static String readFindSubChoice()
	{
		String choiceFindSubChoice = new String();
		try
		{
			do{
		    	System.out.print("please enter your choice(using Numbers): ");        
		    	choiceFindSubChoice = readU();
//				System.out.print("\n\n"+choice+"\n\n"); for debug
//				System.out.print("\n\n"+isChoiceValid(choice)+""+"\n\n"); //for debug
		    }while(!isChoiceValid(choiceFindSubChoice,2));
		}
		catch(IOException e)
    	{
    		e.printStackTrace();
    	}
		return choiceFindSubChoice;
	}
	
	
	private static void Find()
	{
		Info result;
		while(true){
		showFindMenu();
		String choice = readFindChoice();
//		System.out.println(choice + "\n\n\n");
		if(choice.equals("1"))
		    findPerson();
		else
			return;
		}
		
	}
	private static void Add()
	{
		System.out.println("Desktop>Add_________________________");
		String name,gender,email_,phoneNumber;
		try
		{
			System.out.print("Enter the name: ");
			name = readU();
//			System.out1.println();
			System.out.print("Enter the gender: ");
			gender = readU();
//			System.out.println();
			System.out.print("Enter the phoneNumber: ");
			phoneNumber = readU();
//			System.out.println();
			System.out.print("Enter the email: ");
			email_ = readU();
			System.out.println();
			list.add(name, gender, phoneNumber, email_);
		}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
	}

//	private static void Save()
	private static void showSubMenu(String choice)
	{

		switch (choice) {
		case "1":
			

			Find();
			break;
		case "2":
//			System.out.println("Desktop>Save_____________________________");
			Add();
			break;
        
			
		case "3":
			
			System.out.println(list.toString());
			break;
		case "4":
			System.out.println("Desktop>Save_____________________________");
//			Save();
			break;
		case "5":
			System.out.println("\n\nbye :)");
			System.exit(0);
			break;

		default:
			break;
		}
		
	}
	
	
	
	public static boolean isChoiceValid(String chioce,int Max)
	{
		//regix
        int x = 0;
		if(chioce.matches("^\\d+$"))
		{
			x = Integer.parseInt(chioce);
			if (1 <= x && x <= Max)
				return true;
			else
				System.out.println("please Enter the number between 1,2,3,4,5!");
				return false;
		}
		else 
			System.out.println("please Enter a number!");
			return false;
	}
	
	private static void choseList(int Max)
	{
		
		try
	    {	
		String choice = new String();
	    do{
	    	System.out.print("please enter your choice(using Numbers): ");        
	    	choice = readU();
//			System.out.print("\n\n"+choice+"\n\n"); for debug
//			System.out.print("\n\n"+isChoiceValid(choice)+""+"\n\n"); //for debug
	    }while(!isChoiceValid(choice,Max));
        showSubMenu(choice);
	    }catch(IOException e)
    	{
    		e.printStackTrace();
    	}
		
		
	}
	public static void main(String[] args)
    {
		System.out.println("Welcome to your AddressBook:)\n\n");
		list.add("干傻哔", "男", "15618725632", "gansb@3216.com");
		System.out.println(list.toString());
    	while(true)
    	{
    		
    		showMainMenu();
    		choseList(5);
    		

    	}
    }
}
