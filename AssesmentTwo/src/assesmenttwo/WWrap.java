/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assesmenttwo;

import java.util.ArrayList;

/**
 *
 * @author Chathuranga - Pamba
 */
public class WWrap {

    /**
     * @param args the command line arguments
     */
    private final String inputString;
    private final int lineSize;

    private String[] wordList;
    private int[] wordsLength;

    private int memo[][];

    private int numOfWords;

    ArrayList<Integer> al = new ArrayList<>();
    ArrayList<Integer> pos = new ArrayList<>();

    public WWrap(int linesize, String inputString) {
        this.inputString = inputString;
        this.lineSize = linesize;
        wordList = inputString.split(" ");

        wordsLength = new int[wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            wordsLength[i] = wordList[i].length();
        }

        numOfWords = wordList.length;

        for (String string : wordList) {
            System.out.print(string + " ");
        }
        System.out.println("");
        for (int t : wordsLength) {
            System.out.print(t + " ");
        }

        memo = new int[numOfWords + 1][numOfWords + 1];

        createMatrix();
        find();
        int calculateError = calculateError();
        
        System.out.println(calculateError);
        String wrappingString = wrappingString();
        System.out.println(wrappingString);

    }

    private void createMatrix() {

        for (int i = 1; i <= numOfWords; i++) {
            memo[i][i] = lineSize - wordsLength[i - 1];
            for (int j = i + 1; j <= numOfWords; j++) {
                memo[i][j] = memo[i][j - 1] - wordsLength[j - 1] - 1;
            }
        }
        

    }
    
    public String printMatrix(){
        String s="";
        for (int i = 0; i <= numOfWords; i++) {
            for (int j = 0; j <= numOfWords; j++) {
                s+= memo[i][j]+"\t";
            }
            s+="\n";
        }
        return s;
        
    }
    

    private void find() {
        int r = 0;
        int j, h = 0;
        int ans = 0;

        double inf = Double.POSITIVE_INFINITY;

        for (int i = 1; i <= numOfWords; i++) {
            int ind = (int) inf;
            for (j = i; j <= numOfWords; j++) {
                if (memo[i][j] <= ind && memo[i][j] >= 0) {
                    System.out.println(i - 1 + " " + j + " " + memo[i][j]);
                    ans = memo[i][j];
                    ind = memo[i][j];
                    h = j;
                    //System.out.println(h);
                }
            }
            al.add(r, ans);

            i = h;

            pos.add(r, h);

            r++;
        }
        System.out.println(al);
        System.out.println(pos);
        
        //System.out.println(wordList.length);

    }

    public int calculateError() {
        int val = 0;
        for (int x = 0; x < al.size() - 1; x++) {
            val = val + (al.get(x) * al.get(x) * al.get(x));
        }
        return val;
    }

    public  String wrappingString() {

        ArrayList<String> f = new ArrayList<>();

        String newL = "";
        int i = 0;
        for (int ps = 0; ps < pos.size(); ps++) {
            //System.out.println("top " + i);
            while (i < pos.get(ps)) {
                //for(int i=0; i<pos.get(ps); i++){
                newL = newL + wordList[i] + " ";
                i += 1;
            }
            f.add(newL);
            newL = "";
        }
        //System.out.println(newL);
        
        
        String wrappingString="";
        for (String string : f) {
            wrappingString+= string+"\n";
        }
        return wrappingString;
    }

    public static void main(String[] args) {
        //WWrap w = new WWrap(15, "She is happy but is a blue gal. I am all gone.");
    }

}
