import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class functionmain{
	private static String[] temp;
	public static void main(String[] args)
	{
		String p=new Scanner(System.in).next();
		Pattern q=Pattern.compile("[a-z]+[a-z]|^((?![a-z]|\\d|\\*|\\+).)*$");   
		Matcher k=q.matcher(p);
		if(k.find())
		{
			System.out.println("Error,Wrong input.");
		}
		else{
			System.out.println(p);
			String str[]=expression(p);
			Scanner sc = new Scanner(System.in);
			//String command = sc.nextLine();
			long a=System.currentTimeMillis();
			/*if(command.contains("!simplify"))
			{
				Pattern m=Pattern.compile(" ");
				String str0[] = m.split(command);
				int i=1;
				int flag=1;
				while(i<str0.length)
				{
					String val=str0[i].substring(0,1);
					if(!(p.contains(val)))
					{
						flag=0;
						break;
					}
					i++;
				}
				if(flag==1)
				{
					for( i=1;i<str0.length;i++)
					{
						String val=str0[i].substring(0,1);
						int key=Integer.parseInt(str0[i].substring(2,3));
						simplify(str,key,val);
					}
					show(str);
				}
					else
						System.out.println("Error, no variable ");
				
			}
				else
					System.out.println("Error, Wrong input");
			System.out.println("执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");*/
			String command2=new Scanner(System.in).next();
		    str=expression(p);
			if(command2.contains("!d/d"))
			{
				 String val=command2.substring(4,5);
				if(p.contains(val))
				{
					  a=System.currentTimeMillis();
					derivative(str, val);
					show(str);
					System.out.println("\n执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
				}
				else
					System.out.println("Error, no variable ");
			}
				else
					System.out.println("Error, Wrong input");
			
		}
			
			
		}
		
	public static String[] expression(String p)
	{
		Pattern m=Pattern.compile("\\++");
		String str[] = m.split(p);
		return str;
	}
	
	public static void simplify(String[]str,int n,String x)
	{
		for (int i = 0; i < str.length; i++) {
			Pattern p0=Pattern.compile(x+"\\^+\\d");
			Pattern p1=Pattern.compile(x);
			Matcher k0=p0.matcher(str[i]);
			Matcher k1=p1.matcher(str[i]);
			if(k0.find())
			{
				String str0 =k0.group();
				String num0=str[i].substring(0,1);
				String num1=str0.substring(2,3);
         		if (!(Character.isDigit(num0.charAt(0))))
					num0="1";			
				int n0=Integer.parseInt(num0);
				int n1=Integer.parseInt(num1);
				n0=(int) Math.pow(n, n1)*n0;
				String strnew=String.valueOf(n0);
                if(num0.equals("1"))
                {
                	str[i]=str[i].replace(x+"^"+num1,"");
                    str[i]=strnew+str[i];
                }
                else
                {
                	str[i]=str[i].replace("*"+x+"^"+num1,"");
                	str[i]= str[i].replaceFirst( num0,strnew);
                }
			}
			else if(k1.find())
			{

				String str0 =k1.group();
				int flag=0;
				String num0=str[i].substring(0,1);
         		if (!(Character.isDigit(num0.charAt(0))))
         		{
         			num0="1";
         			flag=1;
         		}			
				int n0=Integer.parseInt(num0);
				n0=n0*n;
				String strnew=String.valueOf(n0);
                if(flag==1)
                {
                	if(!str[i].contains("*"+x))
                	  str[i]=str[i].replace(x,"");
                	else
                	{
                		str[i]=str[i].replace("*"+x,"");
                		strnew=strnew+"*";
                	}
                    str[i]=strnew+str[i];
                }
                else
                {
                	str[i]=str[i].replace("*"+x,"");
                	str[i]= str[i].replaceFirst( num0,strnew);
                }

			}
			
		}
	}
	
public static void show(String[]str)
{
	String[] temp=new String[10];
	temp[0]="0";
	int j=1;
	for(int i=0;i<str.length;i++)
	{
		boolean result=str[i].matches("[0-9]+");
		if(result==true)
		{
			temp[0]=String.valueOf(Integer.parseInt(str[i])+Integer.parseInt(temp[0]));
		}
		else if(result==false &&str[i]!="")
		{
			temp[j]=str[i];
			j++;
		}
		
	}
	for(int i=1;i<j;i++)
	{
		if(i==j-1 &&temp[0]=="0")
			   System.out.print(temp[i]);
			else
				System.out.print(temp[i]+"+");
	}
	if(temp[0]!="0")
	  System.out.println(temp[0]);
}
	public static void derivative(String[]str,String x)
	{
		for (int i = 0; i < str.length; i++) {
			Pattern p0=Pattern.compile(x+"\\^+\\d");
			Pattern p1=Pattern.compile(x);
			Matcher k0=p0.matcher(str[i]);
			Matcher k1=p1.matcher(str[i]);
	    if(k0.find())
			{
	    	String str0 =k0.group();
			String num0=str[i].substring(0,1);
			String num1=str0.substring(2,3);
     		if (!(Character.isDigit(num0.charAt(0))))
				num0="1";			
			int n0=Integer.parseInt(num0);
			int n1=Integer.parseInt(num1);
			n0=n1*n0;
			String strnew=String.valueOf(n0);
			String strnew1=String.valueOf(n1-1);
            str[i]=str[i].replace(x+"^"+num1,x+"^"+strnew1);
            if(num0.equals("1"))
                str[i]=strnew+"*"+str[i];
            else
            	 str[i]= str[i].replaceFirst( num0,strnew);
			}
			
			else if(k1.find())
			{
				String num0=str[i].substring(0,1);
	     		if (!(Character.isDigit(num0.charAt(0))))
	     			str[i]=str[i].replace(x,"1");
	     		else
				   str[i]=str[i].replace("*"+x,"");
			}
			else 
		      str[i]="";
		}
	}
}