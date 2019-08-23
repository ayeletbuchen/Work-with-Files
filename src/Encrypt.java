/*
 * This program encrypts a file.
 * It can also be used to decrypt a file, provided that the same password is 
 * used for decryption as was used for encryption
 */
package encrypt;

import java.io.*;

/**
 *
 * @author ayeletbuchen
 * @since April 22, 2018
 * @version 1
 */
public class Encrypt {

    final static int MIN_ARGS = 3;  //minimum nr of arguments needed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        testArgs(args.length);

        final String IFILE = args[0];           //path of input file
        final String OFILE = args[1];           //path of output file
        final String PASSWORD = args[2];        //encryption/decryption key

        encryptFile(IFILE, OFILE, PASSWORD);
    }

    /**
     * Test argument count to see if there are enough command line arguments to
     * run the program. If there are not, exit
     *
     * @param nrArgs the number of command line arguments
     */
    private static void testArgs(int nrArgs)
    {
        if (nrArgs < MIN_ARGS)
        {
            System.out.println("Not enough arguments were entered.");
            System.exit(0);
        }
    }

    /**
     * Open the input and output files. Then use the key to read from the input
     * file and write to the output file as encryption/decryption.
     *
     * @param input the input file path
     * @param output the output file path
     * @param key the encryption/decryption key
     */
    private static void encryptFile(String input, String output, String key)
    {
        try (
                //to read bytes from the input file
                DataInputStream inputFile
                = new DataInputStream(new FileInputStream(input));
                //to write bytes to the output file
                DataOutputStream outputFile
                = new DataOutputStream(new FileOutputStream(output));)
        {
            byte[] password = key.getBytes();  //array of bytes in the password
            int ix = 0;
            System.out.println("Working on it...");

            while (inputFile.available() > 0)//check if input file is at EOF
            {
                //XOR bytes from the input file with bytes from the password 
                //Print the XOR to the output file
                byte inputByte = inputFile.readByte();
                byte encrypted = (byte) (inputByte ^ password[ix]);
                outputFile.writeByte(encrypted);

                //Cycle through the bytes of the password
                if (ix < (password.length - 1))
                {
                    ++ix;
                }
                else
                {
                    ix = 0;
                }
            }

            System.out.println("Done");
        } catch (Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }
}
