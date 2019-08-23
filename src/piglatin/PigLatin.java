/*
 * This program reads files and prints them in Pig Latin
 */
package piglatin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ayeletbuchen
 * @version 1
 * @since 03-07-2018
 */
public class PigLatin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Quit if no file is specified
        if (args.length < 1)
        {
            System.out.println("No file was specified.");
            System.exit(0);
        }

        //Run the program for each file specified
        for (int ix = 0; ix < args.length; ix++)
        {
            try (Scanner inputFile = new Scanner(new File(args[ix])))
            {
                while (inputFile.hasNext()) //Get each line from the file
                {
                    String line = inputFile.nextLine();
                    if (!(line.equals("")))
                    {
                        //Get the words on the line
                        String tLine = line.trim();
                        String[] words = tLine.split("\\s+");

                        //Print each word in Pig Latin
                        for (String word : words)
                     
                        {
                            String capitalWord = word.toUpperCase();

                            //Words that start with a number
                            if (Character.isDigit(capitalWord.charAt(0)))
                            {
                                System.out.print(capitalWord + " ");
                            }

                            //Words that start with a vowel
                            else if (isVowel(capitalWord.charAt(0)))
                            {
                                System.out.print(capitalWord + "VAY ");
                            }

                            else //Words that start with a consonant
                            {
                                //Turn the word into a StringBuilder so that it can be rearranged into Pig Latin
                                StringBuilder PLWord = new StringBuilder(capitalWord);

                                //If the word begins with a consonant and has a vowel in it,
                                //rearrange the word in pig latin
                                while (!isVowel(PLWord.charAt(0)) && hasVowel(capitalWord))
                                {
                                    String consonant = Character.toString(PLWord.charAt(0));
                                    PLWord.deleteCharAt(0);
                                    PLWord.append(consonant);
                                }
                                System.out.print(PLWord + "AY ");
                            }
                        }
                    }
                    //At the end of each line in the file, start a new line in the output
                    System.out.print("\n");
                }
            } catch (IOException exc)
            {
                System.out.println(exc.getMessage());
                System.exit(0);
            }
        }
    }

    /**
     * Check if a letter is a vowel
     * @param c the letter to be checked
     * @return true if vowel, false if not
     */
    private static boolean isVowel(char c) 
    {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check if a word has a vowel in it
     * @param capitalWord the word to be checked
     * @return true if the word has a vowel, false if not
     */
    private static boolean hasVowel(String capitalWord)
    {
        //Isolate each letter and check if it is a vowel
        boolean vowel = false;
        for (int ii = 0; ii < capitalWord.length(); ii++)
        {
            char letter = capitalWord.charAt(ii);
            if (isVowel(letter))
            {
                vowel = true;
            }
        }
        return vowel;
    }
}
