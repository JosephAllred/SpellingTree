import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Joseph Allred
 * Implements 'main' method that tests SpellingTree
 */
public class SpellChecker {
    private static SpellingTree myTree;

    public static void main(String[] args) throws Exception {

        myTree = new SpellingTree();

        readWords("Dictionary.txt");
        myTree.printWords("", myTree.getRoot());
        System.out.println("\n");
        System.out.println("List of misspelled words: ");
        System.out.println("Number of misspelled words: " + checkWords("Test_File.txt"));
    }

    /**
     * Reads words a  file and prints all misspelled words contained in that file
     *
     * @param fileName
     * @return number of misspelled words
     * @throws IOException
     */
    public static int checkWords(String fileName) throws IOException {

        int numWrong = 0;
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scn = new Scanner(fis);
        String word = "";

        while (scn.hasNext()) {
            word = scn.next();
            word = removePunctuation(word);
            if (!myTree.checkWord(word)) {
                numWrong++;
                System.out.println("- " + word);
            }
        }
        System.out.println();
        return numWrong;
    }

    /**
     * Reads a file and adds all words in the file to a SpellingTree
     *
     * @param fileName
     * @throws IOException
     */
    public static void readWords(String fileName) throws IOException {

        FileInputStream fis = new FileInputStream(fileName);
        Scanner scn = new Scanner(fis);
        String word;

        while (scn.hasNext()) {
            word = scn.next();
            myTree.addWord(removePunctuation(word));
        }
    }

    /**
     * @param word: word passed into file which may or may not contain punctuation/capitalization.
     * @return original word stripped of punctuation and capitalization (e.g., 'Ca.t' becomes 'cat'
     */
    public static String removePunctuation(String word) {

        char temp;
        StringBuilder retVal = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            temp = word.charAt(i);
            if (Character.isLetter(temp)) {
                retVal.append(Character.toLowerCase(temp)); // capital letters are appended as lowercase letters
            }
        }
        return retVal.toString();
    }
}