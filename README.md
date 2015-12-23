# Data Encryption Standard Algorithm
 This is a Data encryption and decryption algorithm. It encrypts blocks of size 64 bits.
## Usage

The program cotains a lot of input tables in form of text files.  
The End User should only be concerned of file plain_txt and key.txt.  
The plain_txt file contains the message to be sent and the key.txt contains the encryption key.  
The final decrypted message will be visible to the user on the terminal/Console.  
The intermediate encrypted messages will be visible in the file output_Cipher_text.txt.   

##Example

plain_txt  ---> message: HELLO THIS IS DES ALGO WELCOME 
cipher text---> message:  
0010101101011001110011100100010011000101110101111010101110001000
0010101010010011111010110110100100110011001100000000001011011100
end0010101101011001110011100100010011000101110101111010101110001000
0010101010010011111010110110100100110011001100000000001011011100
end0010101101011001110011100100010011000101110101111010101110001000
0010101010010011111010110110100100110011001100000000001011011100
end0010101101011001110011100100010011000101110101111010101110001000
0010101010010011111010110110100100110011001100000000001011011100
end  

output text---> message: HELLOTHISISDESALGOWELCOME  


## License

Copyright © 2014 Santosh R Yadav  
DES_Tutorial @ http://www.cs.ucsb.edu/~koc/ns/docs/slides/  
Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
