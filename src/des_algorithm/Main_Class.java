package des_algorithm;

public class Main_Class {

	public static void main(String h[]) throws Exception
	{
		DES_Encryption ob=new DES_Encryption();
		ob.getData();               //gets key and plaintxt from file
		ob.permute_key();           //parity drop and permute key
		ob.padding_plaintxt();      //make plaintxt multiple of 64
		
	  for(int i=0;i<ob.multiple;i++)
	   {
	   	
		ob.putting_tempplain_txt(i);//taking 64 bits at a time
		ob.initial_permutation();    //intial permutation of plaintxt
		
		
	  //Now the rounds begin
	    ob.rounds(1);
	    ob.rounds(2);
	    ob.rounds(3);
	    ob.rounds(4);
	    ob.rounds(5);
	    ob.rounds(6);
	    ob.rounds(7);
	    ob.rounds(8);
	    ob.rounds(9);
	    ob.rounds(10);
	    ob.rounds(11);
	    ob.rounds(12);
	    ob.rounds(13);
	    ob.rounds(14);
	    ob.rounds(15);
	    ob.rounds(16);//no swapper in this round
	    
	    ob.final_permutation();
	    
	    ob.put_output_in_op_file(1);
	    ob.round_time=0;
	    
	  }//close of for 
	  
	  ob.put_output_in_op_file(0);
	  //end of encryption
	  
	  DES_Decryption ob2=new DES_Decryption();
		ob2.getData();               //gets key and plaintxt from file
		ob2.permute_key();           //parity drop and permute key
	
		
	 for(int i=0;i<ob2.multiple;i++)
	   {
	   	
		ob2.putting_tempplain_txt(i);//taking 64 bits at a time
		ob2.initial_permutation();    //intial permutation of ciphertxt
		
		
	  //Now the rounds begin
	    ob2.rounds(16);
	    ob2.rounds(15);
	    ob2.rounds(14);
	    ob2.rounds(13);
	    ob2.rounds(12);
	    ob2.rounds(11);
	    ob2.rounds(10);
	    ob2.rounds(9);
	    ob2.rounds(8);
	    ob2.rounds(7);
	    ob2.rounds(6);
	    ob2.rounds(5);
	    ob2.rounds(4);
	    ob2.rounds(3);
	    ob2.rounds(2);
	    ob2.rounds(1);//no swapper in this round
	    
	   	ob2.final_permutation();    //final permutation of ciphertxt
	    
	    ob2.put_output_in_op_file(1);
	    ob2.round_time=17;
	    
	  }//close of for   
	  
	  ob2.put_output_in_op_file(0); 
	  ob2.put_in_text_form();
	}//close of main function
}

