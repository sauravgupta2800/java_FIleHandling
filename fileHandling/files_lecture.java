/*
Absolute file - > whole path ( home->to->file_name)
Relative file - > dir + file_name

//File constructor

File(String path_name)
boolean isFile()
,, exists()
,, canRead()/canWrite()

String getName()
String getPath
long String lastModified()
getAbosolutePath()
long getLength() return in bytes X check correct method
boolean delete() (permanent delete even from recycle bin)
boolean renameTo(File name)
boolean isAbsolute()
boolean isHidden()

File[] listFiles()
boolean createNewFile()   not dir
boolean mkdir() ->create sub dir single
boolean mkdirs() ->create sub dir multiple

import java.io.*;
*/
import java.io.*;
import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;

class Run
{
	public static void main(String...arg)
	{
		File f = new File("race.c");//Relative path
		
		//methods (returns boolean)
		System.out.println("exists :" + f.exists());
		System.out.println("isFile : "+ f.isFile());
		System.out.println("isDir : "+ f.isDirectory());
		System.out.println("canRead : "+ f.canRead());
		System.out.println("canWrite : "+ f.canWrite());
		
		//
		System.out.println();
		System.out.println("isHidden : "+ f.isHidden());
		System.out.println("isAbsolute : " + f.isAbsolute());
		//System.out.println(f.getLength());
		System.out.println("getAbsolutePath : "+ f.getAbsolutePath());
		System.out.println(f.lastModified());
		
		//create new sub sub dir ,create only single sub directory
		/*File d = new File("JavaPrograms1");
		System.out.println("create new sub dir : "+d.mkdir());
		System.out.println("delete single dir : "+ d.delete());*/
		
		//create multiple sub direcrories
		File dr = new File("A//B//C");
		//System.out.println("create multiple sub dirs : "+ dr.mkdirs());
		System.out.println("delete multiple dir : "+ dr.delete());//(deletes only last path location i.e., C not B or A)
		System.out.println("exists :" + dr.exists());
	}
}



//-----------------------------WAP--------------------------------------------



//WAP read a filename from user if matches with list of files in given directory then 
//display its type,length,isRead, isWrite;
class Example
{
	private static String DIRPATH="/home/saurabh/JavaPrograms/";
	private String userEnteredFileName;
	
	
	public static void main(String...args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of file:");
		String name = sc.nextLine();
		name = DIRPATH+name;
		System.out.println(name);//user file eneterd name is here
		File file = new File(name);
		if(file.exists() && file.isFile())
		{
			if(isFilePresent(DIRPATH,name))
			{
				System.out.println("canRead : "+ file.canRead());
				System.out.println("canWrite : "+ file.canWrite());
				if(file.isAbsolute())
				{
					System.out.println("Absolute");
				}
				else
				{
					System.out.println("Relative");
				}
			}
		}
		else
			{
				System.out.println("File name entered is absent");
			}
	}
	//function
	public static boolean isFilePresent(String dirPath, String userEnteredFileName)
	{
		//display all the file names in array
		//if match found return True
		//else False
		File fl = new File(DIRPATH);
		if(fl.exists() && fl.isDirectory())
		{
			File[] files = fl.listFiles();
			System.out.println("in function");
			for(int i=0;i<files.length;i++)
			{
				//System.out.println(files[i]);
				if(files[i].toString().equals(userEnteredFileName))
				{
					System.out.println("match found");
					return true;
				}
			}
		}
		return false;
	}
}


/* git 
git init
git add .
git commit -m "First commit"
git remote add origin REMOTE_REPO_URL
git push origin master
git push origin master --force
*/



/*//(***)
//---------------------------------READING AND WRITING IN FILES------------------
1. ByteStream
2. CharacterStream
*/

/////////////////////////---------------3rd April,18------------------------////

/*
		inputStream -> (1) FileInputStream (2)FilterInputStream (DataInputStream , BufferedInputStream )  (3) ObjectInputStream
object ->
		outputStream  -> (same with output)



OutputStream ->Serialisation -> continuous flow of stream ex. video player
inptStream -> deserialisation

METHODS:
-------
INPUT STREAM:
**********************

read():int  (reads only first byte )                                  
read( byte[]):int (read stream of bytes)
available() //int 
close()    //void
skip()  //long
markSupported() //returns boolean
mark()          //   ,,   void
reset()

*** stream end with -1


OUTPUT STREAM:
************************

write(int b) //void
write( byte []) //void
close() //void
flush() //void  ->to clear data from buffer memory


FileInputStream and FileOutputStream Class
==========================================

IMPORT:
java.io.InputStream
java.io.OutputStream

FileInputStream(File f)
FileOutputStream(String fileName)    
FileOutputStream(File f, boolean append)     // either new file create or not depend upon bool value
FileOutputStream(String fileName , boolean append)   //same case as above   

**your file name should be valid and must exist, otherwise automaticall create it.

int read()/write() throws IOException

*/
class Copy//Reading and writing into another file
{
	public static void main(String...arg) throws IOException
	{
		File f = new File("race.c");
		FileInputStream fin = null;
		FileOutputStream fout = null;
		int c;
		try
		{
			fin = new FileInputStream(f);
			fout = new FileOutputStream("res.txt");
			while((c= fin.read())!=-1)
			{
				fout.write(c);
				//System.out.print((char)c);//output to screen...
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if(fin!=null)
			{
				fin.close();
			}
			if(fout!=null)
			{
				fout.close();
			}
		}
	}
}

//do check once 
class Copy2//Special type of Try   -------------> try with resource
{
	public static void main(String...arg) throws IOException
	{
		File f = new File("race.c");
		//FileInputStream fin = null;
		//FileOutputStream fout = null;
		int c;
		try(
			FileInputStream fin = new FileInputStream(f);
			FileOutputStream fout = new FileOutputStream("res.txt"))
		{
			while((c= fin.read())!=-1)
			{
				fout.write(c);
				System.out.print((char)c);//output to screen...
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
/*

DataInputSteam

java.io.DataInput

readBoolean()
readInt()
readChar()
readByte()
readChar()  //char    
..... (all primitive data)
readLine()	//string
readUTF()   //string //unicode trandform translation

WRITE:
same as above with write
+ writeChar( char type) , writeChars( String type) , writeBytes( String type)


*/  	
class Copy3//DataOutputStream
{
	public static void main(String...arg) throws IOException//checked exceptions
	{
		String str = "files_lecture.java:347: error: illegal start of expression";
		DataOutputStream dout = new DataOutputStream( new FileOutputStream("res.txt"));
		//dout.writeChars("saurav gupta\n");
		dout.writeBytes(str);
		//dout.writeUTF("saurav gupta");
		//dout.writeBoolean(true);
	}
}



/*

java.io.ObjectInputStream
java.io.ObjectOutputStream

readObject() //Object type 
writeObject()

SERIALISATION
=============
java.io.*

process of writing the state of an object to byte stream

useful to save the state of object

restore te object => deserialisation

TRANSIENT: value not be serialized
transient int age;  (CHECK IT ONCE)
----------------------------------------------------------------------

Character Stream
================

abstract class-> Reader and Writer


method is same just the diff is of internal implementations.

------------------------------------------------------------------------------------------
SCANNER: main task to read the data
========
---------------------------------------
PrintWriter (main advantage od thi is that we can store our data in formatted form)
=================
-we can write data in format form
-we can write using text editor

----println , printf , 
PrintWriter( File file / String filename)                      // Class to declare object which will be using methods.

object.print()                //method used to write the data of any types


flush() //cleare the buffer memmory

File f = new File(abc.txt)
Printwriter pr = new PrintWriter(f,true)

***(v. imp)
        pw.flush() // if 'true' is writter as a arg in PrintWriter(f,true) then there is use of flush() method.

*/

class putdata
{
	public static void main(String...str) throws IOException
	{
		DataOutputStream dout = new DataOutputStream( new FileOutputStream("res1.txt"));
		for(int i=1;i<100;i++)
		{
			dout.writeInt(i);	
		}
		DataInputStream din = new DataInputStream( new FileInputStream("res1.txt"));
		System.out.println(din.readLine());
	}
}

// check this question once 
class datadivide
{
	public static void main(String...arg) throws IOException
	{
		File f = new File("res.txt");
		FileInputStream fin = null;
		FileOutputStream fout = null;
		int c;
		String str = null;
		try
		{
			fin = new FileInputStream(f);
			while((c= fin.read())!=-1)
			{
				//fout.write(c);
				str+=(char)c;
				//System.out.print((char)c);//output to screen...
			}
			char ch[] = str.toCharArray();
			System.out.println(str);
			System.out.println(ch.length);
			int pos = 0;
			int j = 0;
			int div = str.length()/10;
			for(int i = 1;i<10;i++)
			{
				j = pos;
				File f1 = new File("output"+i+".txt");
				fout = new FileOutputStream(f1);
				
				for(j = pos ;j < (pos + div) ;j++)
				{
					fout.write(ch[j]);
					//System.out.println(ch[j]);
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if(fin!=null)
			{
				fin.close();
			}
			if(fout!=null)
			{
				fout.close();
			}
		}
	}
}
class abc{
	public static void main(String...arg)
	{
		String str = "saurav";
		char c[] = str.toCharArray();
		for(int i = 0; i<c.length;i++)
		{
			System.out.println(c[i]);
		}
	}
}








