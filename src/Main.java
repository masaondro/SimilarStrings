import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
        	String encoding = System.getProperty("console.encoding", "utf-8");
            Scanner scanner = new Scanner(new File("Input.txt"), encoding);
            int n = scanner.nextInt();
            scanner.nextLine();
            List<String> set1 = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                set1.add(scanner.nextLine());
            }
            int m = scanner.nextInt();
            scanner.nextLine();
            List<String> set2 = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                set2.add(scanner.nextLine());
            }
            scanner.close();

            List<String> output = new ArrayList<>();
            if (set1.size() >= set2.size()) {
            	output = matchSimilarString(set1, set2);
            } else {
            	output = matchSimilarString(set2, set1);
            }
            

            PrintWriter writer = new PrintWriter(new File("Output.txt"));
            for (String s : output) {
                writer.println(s);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static List<String> matchSimilarString(List<String> set1, List<String> set2) {
    	List<String> output = new ArrayList<>();
        for (String s1 : set1) {
            String match = "";
            double maxSimilarity = 0.0;
            for (String s2 : set2) {
                double similarity = calculateSimilarity(s1, s2);
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                    match = s2;
                }
            }
            String result = s1 + ":" + (maxSimilarity >= 0.6 ? match : "?");
            output.add(result);
        }
        return output;
    }

    private static double calculateSimilarity(String s1, String s2) {
    	List<String> setSimilarWords = new ArrayList<>();
        setSimilarWords.add("бетон");
        setSimilarWords.add("цемент");
        setSimilarWords.add("присадкой");
    	
        String[] words1 = s1.split("\\s+");
        String[] words2 = s2.split("\\s+");
        List<String> set1 = new ArrayList<>();
        List<String> set2 = new ArrayList<>();
        
        for (String word : words1) {
        	set1.add(word.toLowerCase());
        }
        for (String word : words2) {
        	set2.add(word.toLowerCase());
        }
       
        int intersection = 0;
        int union = set1.size() + set2.size();
        for (String word1 : set1) {
        	for (String word2 : set2) {
        		if (isTokensFuzzyEqual(word2, word1)) {
        			 intersection++;
                     union--;
        		} else if (setSimilarWords.contains(word1) && setSimilarWords.contains(word2)) {
        			intersection++;
                    union--;
        		}
        	}
        }
        for (String word2 : set2) {
        	for (String word1 : set1) {
        		if (isTokensFuzzyEqual(word1, word2)) {
                     union--;
        		} else if (setSimilarWords.contains(word1) && setSimilarWords.contains(word2)) {
        			 union--;
        		}
        	}
        }
        return (double) intersection / union;
    }
    
    private static boolean isTokensFuzzyEqual(String firstToken, String secondToken) {
        int equalSubtokensCount = 0;
        int SubtokenLength = 2;
        boolean[] usedTokens = new boolean[secondToken.length() - SubtokenLength + 1];
        for (int i = 0; i < firstToken.length() - SubtokenLength + 1; ++i) {
            String subtokenFirst = firstToken.substring(i, i + SubtokenLength - 1);
            for (int j = 0; j < secondToken.length() - SubtokenLength + 1; ++j) {
                if (!usedTokens[j]) {
                    String subtokenSecond = secondToken.substring(j, j + SubtokenLength - 1);
                    if (subtokenFirst.equals(subtokenSecond)) {
                        equalSubtokensCount++;
                        usedTokens[j] = true;
                        break;
                    }
                }    
            }
        }

        int subtokenFirstCount = firstToken.length() - SubtokenLength + 1;
        int subtokenSecondCount = secondToken.length() - SubtokenLength + 1;

        double tanimoto = (1.0 * equalSubtokensCount) / (subtokenFirstCount + subtokenSecondCount - equalSubtokensCount);
        double ThresholdWord = 0.45;
        return ThresholdWord <= tanimoto; 
    }
}