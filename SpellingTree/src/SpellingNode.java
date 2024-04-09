/**
 * @author Joseph Allred
 * implements SpellingNode variables and methods
 */
public class SpellingNode {

    private char value;
    private SpellingNode[] children;
    private boolean correctWord;

    public SpellingNode(char value) {
        this.value = value;
        children = new SpellingNode[26];
        correctWord = false;
    }

    public void setCorrect() {
        correctWord = true;
    }

    public boolean getCorrect() {
        return correctWord;
    }

    /**
     * Adds a child to a SpellingNode's children array, at the correct index. E.g., 'a' is added at index 0,
     * 'b' is added at index 1, 'z' is added at index 25.
     *
     * @param val: The character to be added to the SpellingNode's children array
     * @return true if character is added to children array, false if is already there
     */
    public boolean addChild(char val) { // subtracting 97 gives 'a' a value of 0, 'b' a value of 1...
        if (children[val - 97] == null) { // only add characters if they aren't already there
            SpellingNode temp = new SpellingNode(val);
            children[val - 97] = temp;
            return true;
        } else {
            return false;
        }
    }

    public SpellingNode getChildAt(char val) {
        return children[val - 97];
    }

    public SpellingNode[] getChildren() {
        return children;
    }

    public char getValue() {
        return value;
    }
}