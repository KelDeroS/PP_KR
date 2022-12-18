package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String[] shiftWords(String[] words)
    {
        for (int i = 0; i < words.length; i++)
        {
            String newWord = "";
            for (int j = 0; j < words[i].length(); j++)
            {
                char newChar;
                newChar = (char)((int)words[i].charAt(j) + 1);
                while (newChar > 'z')
                {
                    int diff = newChar - 'z';
                    newChar = (char)('a' + diff - 1);
                }
                newWord += newChar;
            }
            words[i] = newWord;
        }
        return words;
    }

    public static boolean checkShift(String[] words, ArrayList<String> keys) throws IOException {
        words = shiftWords(words);
        int passed = 0;
        for (int i = 0; i < words.length; i++)
        {
            for (int j = 0; j < keys.size(); j++)
            {
                if (words[i].equals(keys.get(j)))
                {
                    passed++;
                    break;
                }
            }
        }
        if (passed == keys.size())
        {
            FileWriter  fileWriter = new FileWriter("output.txt");
            new FileOutputStream("output.txt").close();

            for (int i = 0; i < words.length; i++) {
                fileWriter.write(words[i] + " ");
            }
            fileWriter.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner enscanner = new Scanner(new File("input1.txt"));
        Scanner keyscanner = new Scanner(new File("input2.txt"));

        ArrayList<String> keys = new ArrayList<>();
        while (keyscanner.hasNext())
        {
            keys.add(keyscanner.next());
        }
        String input = "";
        while (enscanner.hasNextLine())
        {
            input += enscanner.nextLine();
        }

        String[] words = input.split("[ ,.\\n\\r\\t-]");
        int shift = 0;

        while (!checkShift(words, keys))
        {
            shift++;
        }
        System.out.println("shift: " + shift);
    }
}