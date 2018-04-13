import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
class Data
{
	public static void main(String[] args) throws IOException
	{
		String filePath = "data.txt";
		int i,j,b;
		int len;//length of data to put in sub files
		int pos= 0;
		ArrayList <Integer> data = new ArrayList<>();
		try
		{
			FileInputStream fin = new FileInputStream(filePath);
			while((b = fin.read())!=-1)
			{
				data.add(b);
			}
			len = data.size()/3;
			fin.close();
			for(i = 0 ; i < 3; i++)//divide  files into 3 sub files
			{
				FileOutputStream fout = new FileOutputStream("res"+i+".txt");
				for(j = pos; j< (pos + len) ;j++)
				{
					fout.write(data.get(j));
				}
				pos = j;
				fout.close();
			}
			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}

class Data2
{
	public static void main(String[] args) throws IOException
	{
		String filePath = "data.txt";
		int aval,i,j;
		try
		{
			FileInputStream fin = new FileInputStream(filePath);    
			aval = fin.available();
			byte b[] = new byte[aval];
			fin.close();
			
			for(i = 0; i < 3; i++)//3 sub files are made
			{
				FileOutputStream fout = new FileOutputStream("result"+i+".txt");
				
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
class Conso
{
	public static void main(String[] args)
	{
		//String text=System.console().readLine(); 
		char[] text=System.console().readPassword(); 
		System.out.println("Text is: "+String.valueOf(text));  
	}
}