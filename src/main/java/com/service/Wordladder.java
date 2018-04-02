/*This program create a word ladder from one word to another.After two words and the filename are entered, it store the file into a set as dictionary, then create a queue of stacks, each stack is a ladder.
For each stack, the program will find all neighbor words of its top word, create a new stack by pushing the word into a copy of the stack for each word, and push the new stack into the queue one by one.
Meanwhile, old stacks will be deleted. Once a complete ladder is found, it will be printed.*/
package com.service;
import java.io.*;
import java.util.*;

public class Wordladder
{
    //initiate dictionary
    public static boolean initiate(List<String> d) throws IOException
    {
        System.out.println("Dictionary file name:");
        String filename,s;
        filename = "E:/Github Repositories/Service-practice/src/main/resources/static/dictionary.txt";
        File file = new File(filename);
        if(file == null)
        {
            return false;
        }
        BufferedReader fr = new BufferedReader(new FileReader(file));
        while((s = fr.readLine()) != null)
        {
            d.add(s);
        }
        return true;
    }

    //create wordladders
    public static String createLadder(List<String> dict, final String begin, final String end)
    {
        String result = "";
        int l = begin.length();
        int count = 0;
        List<String>dictionary = new ArrayList<String>();
        for(String a : dict)
        {
            if(l == a.length())dictionary.add(a);
        }
        Queue<Stack<String>>ladders = new LinkedList<Stack<String>>();
        Stack<String>firstLadder = new Stack<String>();
        firstLadder.push(begin);
        ladders.offer(firstLadder);
        while(!ladders.isEmpty())
        {
            Stack<String>currentLadder = ladders.poll();
            final String currentWord = currentLadder.peek();
            for(int i = 0; i < l; ++i)
            {
                for(char j = 'a'; j <= 'z'; ++j)
                {
                    char[] arr = currentWord.toCharArray();
                    arr[i] = j;
                    String neighborWord = new String(arr);
                    if(dictionary.contains(neighborWord))
                    {
                        if(neighborWord.equals(end))
                        {
                            System.out.print("A ladder from ");
                            System.out.print(end);
                            System.out.print(" back to ");
                            System.out.print(begin);
                            System.out.print(": \n");
                            currentLadder.push(end);
                            String ans;
                            while(!currentLadder.isEmpty())
                            {
                                ans = currentLadder.pop();
                                System.out.print(ans);
                                System.out.print(' ');
                                result += ans;
                                result += " ";
                                count++;
                            }
                            System.out.print("\nThe length of the ladder is:");
                            System.out.print(count);
                            System.out.print('\n');
                            return result;
                        }
                        else
                        {
                            Stack<String>temp = (Stack<String>)currentLadder.clone();
                            temp.push(neighborWord);
                            ladders.offer(temp);
                            dictionary.remove(neighborWord);
                        }
                    }
                }
            }
        }
        System.out.print("No word ladder found from ");
        System.out.print(end);
        System.out.print(" back to ");
        System.out.print(begin);
        System.out.print(".\n");
        return "...";
    }

    //verify the validity of strings(i.e. check if the string is made of letters)
    public static boolean isValid(final String s)
    {
        char[] arr = s.toCharArray();
        for(char c : arr)
        {
            if(!((c >= 'a' && c <= 'z') || (c > 'A' && c < 'Z')))return false;
        }
        return true;
    }

    public static String runLadder( String begin, String end ) throws IOException
    {
        List<String> dict = new ArrayList<String>();
        if(initiate(dict))
        {
            while(true)
            {
                if(begin.equals(""))
                {
                    return "Have a nice day!";
                }
                if(!isValid(begin))
                {
                    System.out.print("Invalid input!");
                    continue;
                }
                if(end.equals(""))
                {
                    return "Have a nice day!";
                }
                if(!isValid(end))
                {
                    System.out.print("Invalid input!\n");
                    continue;
                }
                if(begin.equals(end))
                {
                    System.out.print("The two words must be different\n");
                    continue;
                }
                if(begin.length() != end.length())
                {
                    System.out.print("The two words must be of the same length\n");
                    continue;
                }
                begin = begin.toLowerCase();
                end = end.toLowerCase();
                if(!(dict.contains(begin) && dict.contains(end)))
                {
                    System.out.print("The two words must be in the dictionary.\n");
                    continue;
                }
                return createLadder(dict, begin, end);
            }
        }
        else
        {
            return "Have a nice day!";
        }
    }

    public static void main(String[] args) throws IOException
    {
        System.out.print(runLadder("end", "pin"));
    }
}