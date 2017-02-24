/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mykeygen;

/**
 *
 * @author Timothy Kang
 */

import java.io.*;
import java.math.BigInteger;
import java.util.Random;


class MyKeyGen{
	
	public static void main(String[] args) throws IOException{
		
            //variables from given equations
            
		BigInteger p, q;
		BigInteger d, e;
		BigInteger n;
		BigInteger PHI;
		//byte[] byteArray;
		
                // output readers
		//ObjectOutputStream pub = new ObjectOutputStream(new FileOutputStream("pubkey.rsa"));
		//ObjectOutputStream priv = new ObjectOutputStream(new FileOutputStream("privkey.rsa"));
		
                //create a prime number from BigInteger
		//p = new BigInteger(512, 15, new Random());					
		//q = new BigInteger(512, 15, new Random());
                
                p = BigInteger.probablePrime(512, new Random());
                q = BigInteger.probablePrime(512, new Random());
	
		 //given equations
		n = p.multiply(q);											
		PHI = p.subtract(BigInteger.valueOf(1));					
		PHI = PHI.multiply(q.subtract(BigInteger.valueOf(1)));		
		
		
                
                do{
			e = new BigInteger(512, new Random());
		} while(e.gcd(PHI).compareTo(BigInteger.valueOf(1)) != 0 	//GCD of e and PHI must be 1
				|| e.compareTo(BigInteger.valueOf(1)) < 1	//e must be > 1
				|| e.compareTo(PHI) > -1);			//e must be < PHI(n)
		
                //given equation for modInverse
		d = e.modInverse(PHI);		

/*
		//convert to byte array
		byteArray = e.toByteArray();
                //write to rsa
		pub.write(byteArray);
		
                //same for rest
		byteArray = d.toByteArray();
		priv.write(byteArray);
		
		pub.write('\n');									
		priv.write('\n');
		
		byteArray = n.toByteArray();
		pub.write(byteArray);
		priv.write(byteArray);
		
		//close files
		pub.close();
		priv.close();
                
                */
                
                PrintWriter pubkeyOut = new PrintWriter("pubkey.rsa");
                pubkeyOut.println(e);
                pubkeyOut.println(n);
        
                PrintWriter privkeyOut = new PrintWriter("privkey.rsa");
                privkeyOut.println(d);
                privkeyOut.println(n);
        
                pubkeyOut.close();
                privkeyOut.close();
		
	}
		
}

