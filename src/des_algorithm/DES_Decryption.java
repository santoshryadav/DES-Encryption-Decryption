package des_algorithm;
import java.util.*;
import java.io.*;
import java.lang.*;

public class DES_Decryption {

	 BufferedReader input;
	 String line="";
	 char whatmsg[]={'$','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	 String plaintext="";
	 String key="";
	 int plaintxt[];
	 int Temp_plaintxt[]=new int[65];
	 int Temp_plaintxt2[]=new int[65];
	 public int multiple;
	 int kee[]=new int[57];
	 int Temp_kee[]=new int[65];
	 int count=1;
	 int round_key[]=new int[49];
	 int feistal_output[]=new int[33];
	 public int round_time=17;//one more than req rounds
		
	public void getData()  throws Exception //this will get the plaintxt and key from file and convert them into binary form
	 {
	   input=new BufferedReader(new FileReader(new File("output_Cipher_text.txt")));
	    
	   line=input.readLine();
	      
	   while(!line.equals("end"))
	   {
	   	plaintext=plaintext+line;
	   	line=input.readLine();
	   }	

		 System.out.println("Cipher text in String binary.....");
		 
		 System.out.println(plaintext);
		 System.out.println();
		 System.out.println();

		 plaintxt=new int[plaintext.length()];
	 	
	 	 multiple=plaintxt.length/64;
	 	
	 	for(int i=0;i<plaintext.length();i++)
		  {
		  	if(plaintext.charAt(i)=='0')
		  	  plaintxt[i]=0;
		  	else
		  	  plaintxt[i]=1;  
	  
		  }//close of for
		  
		  System.out.println("Ciphertxt in int binary form.....");
		  System.out.println();
		  for(int i=0;i<plaintxt.length;i++)
	      System.out.print(plaintxt[i]);
	      System.out.println();
	      
		  System.out.println(multiple);

		 input=new BufferedReader(new FileReader(new File("inputs\\key.txt")));
		 key=input.readLine(); 
		 
		 System.out.println("key in binary string........");
		 System.out.println(key);
		 System.out.println();
 
		 System.out.println("temp key in int binary.......");
		  
		 for(int i=0;i<64;i++)
		  {
		  	if(key.charAt(i)=='0')
		  	  Temp_kee[i+1]=0;
		  	else
		  	  Temp_kee[i+1]=1;
		  	  
		  	System.out.print(Temp_kee[i+1]);  
		  	
		  } //close of for
		  
		  
		  System.out.println();
	
	 }//close of getData()
	 
	public void permute_key() throws Exception
	 {
	 	input=new BufferedReader(new FileReader(new File("inputs\\paritydrop_n_permute.txt")));
	 	line=input.readLine();
	 	
	 	while(!line.equals("end"))
	 	{
	 	   String x[]=line.split(" ");
	 	   
	 	   for(int i=0;i<x.length;i++)
	 	   {
	 	   	 int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	 kee[count++]=Temp_kee[index];
	 	   }
	 	   
	 	   line=input.readLine();
	 	   	
	 	}//close of while
	 	
	 	
	 	System.out.println("permuted key......"); 
	 	for(int i=1;i<57;i++)
	 	  System.out.print(kee[i]);
	 	  
	 	 System.out.println(); 
	 	 System.out.println(); 
	 	 
	 	 count=1;//while goin restore the value 
	 	
	 }//cloe of permute_key()
	 

	 public void putting_tempplain_txt(int n)
	 {
	   	for(int i=0;i<64;i++)
	   	 Temp_plaintxt2[i+1]=plaintxt[n*64+i];

	   	 System.out.println("Initial original 64 bit cipher txt,...");
	   	 System.out.println(); 
	   	for(int i=1;i<65;i++)
	   	 {
	   	 	System.out.print(Temp_plaintxt2[i]);
	   	 	if(i==32)
	   	 	 System.out.print("     ");
	   	 }	
	   	 System.out.println();
	   	 
	   	
	   	 System.out.println();
	   	
	 }//close of putting
	 
	 public void initial_permutation() throws Exception
	 {
	 	count=1;
	    input=new BufferedReader(new FileReader(new File("inputs\\initial_permu.txt")));
	    line=input.readLine();
	      
	      
	    while(!line.equals("end"))
	 	{
	 	   	
	 	   String x[]=line.split(" ");
	 	   
	 	   for(int i=0;i<x.length;i++)
	 	   {
	 	   	 int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	 Temp_plaintxt[count++]=Temp_plaintxt2[index];
	 	   }
	 	   
	 	   line=input.readLine();
	 	   	
	 	}//close of while
	 	
	 	
	 	System.out.println("Initial permuted Cipher txt......"); 
	 	for(int i=1;i<65;i++)
	 	  {
	 	  	System.out.print(Temp_plaintxt[i]);
	 	  	if(i==32)
	   	 	 System.out.print("     ");
	 	  }	
	 	  
	 	 System.out.println(); 
	 	 System.out.println(); 
	 	 
	 	 count=1;//while goin restore the value 
	   
	 }//close of initial permutation
	 
	 
	 public void rounds(int n) throws Exception
	   {
	   	 round_time--;
	   	 round_key_generator(n);
	   	 
	   	 feistal();                 //after the call the output of feistal function is set
	   	 
	   	 //now XOred operation with output of feistal
	   	 System.out.println("feistal exored......");
	   	 
	   	 for(int i=1;i<33;i++)
	   	  feistal_output[i]=Temp_plaintxt[i]^feistal_output[i];
	   	  
	   	 displayFeistal();
	   	 System.out.println();
	   	 
	   	 //Swapper last step
	   	 if(round_time!=1)
	   	 swapper();               //after this Temp_plaintxt[] has new data for 2nd round
	   	 else
	   	  {
	   	  	for(int i=1;i<33;i++)
	   	    Temp_plaintxt[i]=feistal_output[i];  
	   	  }//since last round doesnt have swapper
	   	  
	   	  
	   	  for(int i=1;i<65;i++)
	  	  {
	  	  	System.out.print(Temp_plaintxt[i]);
	  	  	if(i==32)
	  	  	System.out.print("    ");  
	  	  }
	  	  System.out.println(); 
	   	
	   }//close of rounds
	   
	 public void round_key_generator(int n) throws Exception
	 {
	   int temp[]=new int[57];
	    for(int i=1;i<57;i++)
	     temp[i]=kee[i];         //dont assign directly else originality will b lost
	     
	    System.out.println();
	    System.out.println();
	     
	   for(int i=1;i<=n;i++)
	    {
	    
	         if(i==1||i==2||i==9||i==16)
	           {
	       	     shift_left(1,temp);
	           }
	         else
	           {
	      	     shift_left(2,temp);
	           }  
	     
	    }//close of for 
	    
	    System.out.println("temp array after shifting........");
	    System.out.println();
	    for(int i=1;i<57;i++)
	     {
	     	System.out.print(temp[i]);
	     	if(i==28)
	     	System.out.print("   ");
	     }	
	    
	    
	    input=new BufferedReader(new FileReader(new File("inputs\\compression_dbox.txt")));
	    line=input.readLine();
	      
	      
	    while(!line.equals("end"))
	 	{
	 	   
	 	   String x[]=line.split(" ");
	 	   
	 	   for(int i=0;i<x.length;i++)
	 	   {
	 	   	 int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	 round_key[count++]=temp[index];
	 	   }
	 	   
	 	   line=input.readLine();
	 	   	
	 	}//close of while
	 	
	 	System.out.println();
	 	System.out.println();
	 	System.out.println("Round key of round  "+n+"  after compression......"); 
	 	for(int i=1;i<49;i++)
	 	  System.out.print(round_key[i]);
	 	  
	 	 System.out.println(); 
	 	 System.out.println();
	 	 
	 	 count=1;
	 	
	 }//close of round key generator 
	 
	 public void shift_left(int bits,int temp[])
	   {
	   	 if(bits==1)
	   	  {
	   	  	int first=temp[1];
	   	   	 for(int i=1;i<28;i++)
	   	   	   temp[i]=temp[i+1];
	   	   	temp[28]=first;        //this four line for first 28 bits n next for next 28 bits
	   	   	
	   	   	  first=temp[29];
	   	   	  for(int i=29;i<56;i++)
	   	   	   temp[i]=temp[i+1];
	   	   	temp[56]=first;  
	   	  	
	   	  }
	   	 else   //2 bit shift
	   	  {
	   	  	
	   	  	for(int k=1;k<=2;k++)
	   	  	{
	   	  	 
	   	  	   int first=temp[1];
	   	   	   for(int i=1;i<28;i++)
	   	   	    temp[i]=temp[i+1];
	   	   	   temp[28]=first;        
	   	   	
	   	   	    first=temp[29];
	   	   	   for(int i=29;i<56;i++)
	   	   	    temp[i]=temp[i+1];
	   	   	   temp[56]=first;  
	   	  	
	        }//close of for k     	  
	   	  }//close of else
	   	  
	   }//close of shift()
	   
	   
	  public void feistal() throws Exception
	   {
	   	 int bits_32_of_plaintxt[]=new int[33];
	   	  
	   	   for(int i=1;i<33;i++)
	   	     {
	   	     	bits_32_of_plaintxt[i]=Temp_plaintxt[32+i];	
	   	     	System.out.print(bits_32_of_plaintxt[i]);
	   	     }
	   	     System.out.println();
	   	     System.out.println();	
	   	       
	   	    
	   	 int bits_32_2_48_of_plaintxt[]=new int[49];
	   	 
	   	//step 1 Expansion
	   	
	   	input=new BufferedReader(new FileReader(new File("inputs\\expand_dbox.txt")));
	    line=input.readLine();
	      
	      
	    while(!line.equals("end"))
	 	{
	 	   
	 	   String x[]=line.split(" ");
	 	   
	 	   for(int i=0;i<x.length;i++)
	 	   {
	 	   	 int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	 bits_32_2_48_of_plaintxt[count++]=bits_32_of_plaintxt[index];
	 	   }
	 	   
	 	   line=input.readLine();
	 	   	
	 	 }//close of while
	 	 
	 	 System.out.println("expanded 32 to 48 bits of plaintxt......");
	 	 for(int i=1;i<49;i++)
	   	   System.out.print(bits_32_2_48_of_plaintxt[i]);
	   	    
	   	     System.out.println();
	   	     System.out.println();	
	   	  
	   	  
	   	  count=1;    //restored
	   	 //end of step 1    
	   	 
	   	 //step 2 XOred
	   	  
	   	  System.out.println("EXored 48 bit of plaintxt.....");
	   	  for(int i=1;i<49;i++)
	   	   {
	   	   	bits_32_2_48_of_plaintxt[i]=bits_32_2_48_of_plaintxt[i]^round_key[i];   
	   	   	System.out.print(bits_32_2_48_of_plaintxt[i]);
	   	   }
	   	   
	   	   
	   	   System.out.println();
	   	   System.out.println();
	   	   
	   	//end of step 2  
	   	
	   	//step 3 S-Boxes
	   	   int aq[]=new int[7];
	   	   int index1=1;
	   	   for(int i=1;i<49;i++)
	   	    {
	   	      aq[index1++]=bits_32_2_48_of_plaintxt[i];
	   	      if(i%6==0)
	   	       {
	   	       	Sbox(aq,(i/6));	
	   	       	index1=1;
	   	       }	
	   	    }//close of for 
	   	    
	   	  System.out.println("Bfore Straight box.......feistal output.......");
	   	  displayFeistal();   //bfore straight box
	   	   
	   	  count=1;  
	   	//end of step 3  
	   	
	   	 //step 4 Straight D-box
	   	   int strait[]=new int[33];
	   	    for(int i=1;i<33;i++)
	   	     strait[i]=feistal_output[i];
	   	     	
	   	    
	   	    System.out.println(); 
	   	   input=new BufferedReader(new FileReader(new File("inputs\\strait_dbox.txt")));
	   	   line=input.readLine();
	       
	      
	        while(!line.equals("end"))
	 	     {
	 	        
	 	        String x[]=line.split(" ");
	 	   
	 	        for(int i=0;i<x.length;i++)
	 	         {
	 	   	       int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	       feistal_output[count++]=strait[index];
	 	         }
	 	   
	 	        line=input.readLine();
	 	   	
	 	     }//close of while
	 	     
	 	    
	 	     System.out.println("After Straight box.......feistal output......."); 
	 	     displayFeistal();  //after straight box
	 	     
	 	     
	 	   //end of step 4  
	   	  count=1;   
	   	 
	   }//close of feistal	
	   
	   
	   
	  public void Sbox(int aq[],int box_no) throws Exception
	  {
	  	
	  	String column=""+aq[2]+aq[3]+aq[4]+aq[5];
	  	System.out.println(column);
	  	System.out.println(Integer.parseInt(column,2));
	  	input=new BufferedReader(new FileReader(new File("inputs\\s_boxes.txt")));
	    line=input.readLine();
	    while(!line.equals("sbox_"+box_no))
	     line=input.readLine();
	     
	     
	     System.out.println(line);
	     
	     String x[];
	    
	    if(aq[1]==0&&aq[6]==0)
	     {
	       line=input.readLine();  //first row	
	       
	       x=line.split(" ");
	       
	       String p=Integer.toBinaryString(Integer.parseInt((x[Integer.parseInt(column,2)])));
	       System.out.println(p);
	       
	       put_Feistal(p);
	       	
	       	 
	     } //close	
	     
	        	  
	    if(aq[1]==0&&aq[6]==1)
	     {
	       line=input.readLine();
	       line=input.readLine();  //second row	
	       
	       x=line.split(" ");
	       
	       String p=Integer.toBinaryString(Integer.parseInt((x[Integer.parseInt(column,2)])));
	       System.out.println(p);
	       
	       put_Feistal(p);
	      	 
	     }//close
	     
	     
	    if(aq[1]==1&&aq[6]==0)
	     {
	     	line=input.readLine();
	     	line=input.readLine();
	     	line=input.readLine();  //third row	
	     	
	       x=line.split(" ");
	       
	       String p=Integer.toBinaryString(Integer.parseInt((x[Integer.parseInt(column,2)])));
	       System.out.println(p);
	       
	       put_Feistal(p);
	      	
	     }//close
	     
	     
	    if(aq[1]==1&&aq[6]==1)
	     {
	       line=input.readLine();
	       line=input.readLine();
	       line=input.readLine();
	       line=input.readLine();  //fourth row	
	       
	       x=line.split(" ");
	       
	       String p=Integer.toBinaryString(Integer.parseInt((x[Integer.parseInt(column,2)])));
	       System.out.println(p);
	       
	       put_Feistal(p);
	      	
	     } //close  
	     
	      
	  }//close of Sbox 
	  
	  
	  public void put_Feistal(String p)
	  {
	  	
	  	if(p.equals("1")||p.equals("0"))
		  p="000"+p;
			   
	    if(p.equals("10") || p.equals("11"))
	      p="00"+p;
			   
	    if(p.equals("100") || p.equals("101") || p.equals("110") || p.equals("111")) 
	      p="0"+p;
	      
	      
	      
	     for(int i=0;i<4;i++)
	       if(p.charAt(i)=='0')
	         feistal_output[count++]=0;
	       else
	         feistal_output[count++]=1;
	          
	             
	  	System.out.println();
	  }//close of putFeistal() 
	  
	  
	  public void displayFeistal()
	  {
	  	System.out.println();
	  
	  	
	  	for(int i=1;i<33;i++)
	  	 {
	  	 	System.out.print(feistal_output[i]);
	  	 	if(i%4==0)
	  	 	System.out.print("   ");
	  	 }
	  	 
	  	System.out.println(); 
	  	System.out.println();	
	  	 
	  }//close of display  
	  
	  public void swapper()
	  {
	  	 for(int i=1;i<33;i++)
	  	  {
	  	  	Temp_plaintxt[i]=Temp_plaintxt[32+i];
	  	  	Temp_plaintxt[32+i]=feistal_output[i];
	  	  }
	  	  
	  
	  	  System.out.println();
	  	  count=1;
	  }//close of swapper
	  
	  
	  
	 public void final_permutation() throws Exception
	  {
	    count=1;
	    for(int i=1;i<65;i++)
	    Temp_plaintxt2[i]=Temp_plaintxt[i];
	    
	    input=new BufferedReader(new FileReader(new File("inputs\\final_permu.txt")));
	    line=input.readLine();
	      
	      
	    while(!line.equals("end"))
	 	{
	 	   	
	 	   String x[]=line.split(" ");
	 	   
	 	   for(int i=0;i<x.length;i++)
	 	   {
	 	   	 int index=Integer.parseInt(x[i]);
	 	   	 
	 	   	 Temp_plaintxt[count++]=Temp_plaintxt2[index];
	 	   }
	 	   
	 	   line=input.readLine();
	 	   	
	 	}//close of while
	 	
	 	
	 	System.out.println("Final permuted plain txt......"); 
	 	for(int i=1;i<65;i++)
	 	  {
	 	  	System.out.print(Temp_plaintxt[i]);
	 	  	if(i==32)
	   	 	 System.out.print("     ");
	 	  }	
	 	  
	 	 System.out.println(); 
	 	 System.out.println(); 
	 	 
	 	 count=1;//while goin restore the value 
	   
	   }//close of final permutation
	     
	   public void put_output_in_op_file(int from_where) throws Exception
	   {
	   	  BufferedWriter output=new BufferedWriter(new FileWriter("output_plaintxt_text_in_binary.txt",true));
	           //Start writing to the output stream
	          
	          if(from_where==1) 
	           {
	           
	           for(int i=1;i<65;i++)
	            output.write(Temp_plaintxt[i]+"");
	            output.newLine();
	            
	           }//close of if
	          else
	           output.write("end"); 
	           
	            
	            output.flush();
	            output.close();
	   
	   }//close of outputfile()
	   
	   
	   public void put_in_text_form()throws Exception
	   {
	   	  plaintext="";
	   	  input=new BufferedReader(new FileReader(new File("output_plaintxt_text_in_binary.txt"))); 
	   	  line=input.readLine();
	   	  
	   	  while(!line.equals("end"))
	   	   {
	   	   	 plaintext=plaintext+line;
	   	   	 line=input.readLine();
	   	   }
	   	   
	   	   System.out.println(plaintext);
	   	   System.out.println();
	   	   System.out.println();
	   	   line="";
	   	  
	   	   try{
	   	   for(int i=1;i<=plaintext.length();i++)
	   	    {
	   	    	line=line+plaintext.charAt(i-1);
	   	    	if(i%5==0)
	   	    	 {
	   	    	 	System.out.print(whatmsg[Integer.parseInt(line,2)]);
	   	    	 	line="";
	   	    	 }
	   	    }}catch(Exception e)
	   	   {
	   	    	
	   	   }
	   	   
	   	  System.out.println();
	   	  System.out.println(); 
	   	   
	   	   
	   }//close of put_in_textform()
}

