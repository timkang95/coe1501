/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylzw;

/**
 *
 * @author Tim
 */
/*************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/

public class MyLZW {
    private static final int R = 256;        // number of input chars
    private static int L;       // number of codewords = 2^W
    private static int W;         // codeword width 9<=W<=16

    public static void compress(String mode) { 
        W = 9;
        int counter = 0;
        if(mode.equals("n")){
            String input = BinaryStdIn.readString();
            TST<Integer> st = new TST<Integer>();
            for (int i = 0; i < R; i++)
                st.put("" + (char) i, i);
            int code = R+1;  // R is codeword for EOF

            while (input.length() > 0) {
                counter = 0;
                //break if W exceed 16
                if(W>16){
                    W = 16;
                    break;
                }
                //adds values to 9 <= X <= 16
                while(counter < Math.pow(2,W)){
                    String s = st.longestPrefixOf(input);  // Find max prefix match s.
                    BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
                    int t = s.length();
                    if (t < input.length() && code < Math.pow(2,W))    // Add s to symbol table.
                        st.put(input.substring(0, t + 1), code++);
                    input = input.substring(t);            // Scan past s in input.
                    counter++; //counter to tell the filled up L to break
                }
                W++;
            }
            BinaryStdOut.write(R, W);
            BinaryStdOut.close();
        }
        if(mode.equals("r")){
            
        }
        if(mode.equals("m")){
            
        }
    } 


    public static void expand() {
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        BinaryStdOut.close();
    }



    public static void main(String[] args) {
        String random;
        if(args[0].equals("-")){
            random = args[2];
            compress(random);
           // compress can have n r m
           //       n for do nothing mode
           //       r for reset mode
           //       m for moniter mode
        }
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
