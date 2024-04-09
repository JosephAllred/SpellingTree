/**
 * @author Joseph Allred
 * Implements SpellingTree variables and methods
 */
public class SpellingTree {
    private SpellingNode root;

    public SpellingTree() {
        root = new SpellingNode(' ');
    }

    public SpellingNode getRoot() {
        return root;
    }

    /**
     * Adds word to a SpellingTree if SpellingTree does not already have it.
     * @param word: The word to be added to the SpellingTree
     */
    public void addWord(String word) {
        if (checkWord(word)) {
            return;
        }
        SpellingNode temp = root;
        for (int i = 0; i < word.length(); i++) { // iterate through word and add characters individually
            if (temp.getChildAt(word.charAt(i)) == null) { // only add characters that  don't exist in tree already
                temp.addChild(word.charAt(i));
            }
            temp = temp.getChildAt(word.charAt(i));
        }
        temp.setCorrect(); // no parameter, just sets correctWord to true
    }

    /**
     * @param word
     * @return 'true' if input word is contained by the SpellingTree, false if it is not.
     */
    public boolean checkWord(String word) {

        SpellingNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.getChildAt(word.charAt(i)) == null) {
                return false; //if 'temp' node's children do not contain next letter in word, word does not exist in tree
            } else {
                temp = temp.getChildAt(word.charAt(i)); // move 'temp' node down the tree
            }
        }
        return temp.getCorrect(); /* ensures method does not return true for a partial word. E.g., 'back' will not
        return true if the tree contains 'backlash'
        */
    }

    /**
     * Recursive, in-order algorithm that prints all words contained in a SpellingTree in alphabetical order.
     * @param subWord: String containing the aggregate characters (values) from the root to the current node.
     * @param c: The node on which the recursive call begins (starting  with the Root of a SpellingTree)
     */
    public void printWords(String subWord, SpellingNode c) {
        SpellingNode tempNode = c; // for simplicity's sake :)

        if (tempNode.getCorrect()) { // check if the current node's value is the end of a word
            System.out.print(subWord + " ");
        }
        for (int i = 0; i < tempNode.getChildren().length; i++) {
            if (tempNode.getChildren()[i] != null) {
                printWords(subWord + tempNode.getChildren()[i].getValue(), tempNode.getChildren()[i]);
                /* 'subWord' parameter passed into recursive call consists of temp's subWord plus the value of
                temp's child node at index i */
            }
        }
    }
}