package playfair;

import java.util.Scanner;

public class PlayFair {

    static char table[][] = new char[5][5];
    static String keyWord = new String();

    public static String checkKey(String key) {
        String formatedKey = new String();
        boolean repeatedChar = false;
        String myKey = new String();

        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == 'J') {
                myKey = myKey + 'I';

            } else {
                myKey = myKey + key.charAt(i);
            }

        }
        formatedKey = formatedKey + myKey.charAt(0);
        for (int i = 1; i < myKey.length(); i++) {
            for (int j = 0; j < formatedKey.length(); j++) {
                if (myKey.charAt(i) == formatedKey.charAt(j)) {
                    repeatedChar = true;
                }
            }
            if (repeatedChar == false) {
                formatedKey = formatedKey + myKey.charAt(i);
            }
            repeatedChar = false;
        }
        return formatedKey;
    }

    public static void charactersMatrixForm(String key) {

        String letters = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        keyWord = checkKey(key);

        int indexOfLetters = 0;
        int counter = 0;

        if (keyWord.length() > 25) {
            return;
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {

                    if (counter < keyWord.length()) {
                        table[i][j] = keyWord.charAt(counter);
                        counter++;
                    } else {
                        while (indexOfLetters < letters.length()) {

                            if (keyWord.indexOf(letters.charAt(indexOfLetters)) == -1) {
                                table[i][j] = letters.charAt(indexOfLetters);
                                indexOfLetters++;
                                break;
                            } else {
                                indexOfLetters++;
                            }
                        }

                    }
                }

            }
        }

    }

    public static String[] divideTextPlainIntoPairs(String plainText) {

        String plain = editingPlainText(plainText);
        int size = plain.length();
        if (size % 2 != 0 && plain.charAt(size - 1) == 'X') {
            size++;
            plain = plain + 'V';
        } else if (size % 2 != 0 && plain.charAt(size - 1) != 'X') {
            size++;
            plain = plain + 'X';
        }
        String[] pairsOfChars = new String[size / 2];
        int range = 0;
        for (int i = 0; i < size / 2; i++) {
            pairsOfChars[i] = plain.substring(range, range + 2);
            range = range + 2;
        }
        return pairsOfChars;

    }

    public static int[] getCharIndexes(char letter) {

        int[] equivelantLetterIndex = new int[2];

        if (letter == 'J') {
            letter = 'I';
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j] == letter) {
                    equivelantLetterIndex[0] = i;
                    equivelantLetterIndex[1] = j;
                    break;
                }
            }
        }
        return equivelantLetterIndex;
    }

    public static String playFairEncryption(String plain) {

        String[] pairs = divideTextPlainIntoPairs(plain);
        String cipher = new String();
        char firstCharOfThePair;
        char secondCharOfThePair;
        int[] indexesOfFirstCharOfThePair = new int[2];
        int[] indexesOfSecondCharOfThePair = new int[2];
        for (int i = 0; i < pairs.length; i++) {

            firstCharOfThePair = pairs[i].charAt(0);
            secondCharOfThePair = pairs[i].charAt(1);
            indexesOfFirstCharOfThePair = getCharIndexes(firstCharOfThePair);
            indexesOfSecondCharOfThePair = getCharIndexes(secondCharOfThePair);

            if (indexesOfFirstCharOfThePair[0] == indexesOfSecondCharOfThePair[0]) {
                if (indexesOfFirstCharOfThePair[1] < 4) {
                    indexesOfFirstCharOfThePair[1]++;
                } else {
                    indexesOfFirstCharOfThePair[1] = 0;
                }
                if (indexesOfSecondCharOfThePair[1] < 4) {
                    indexesOfSecondCharOfThePair[1]++;
                } else {
                    indexesOfSecondCharOfThePair[1] = 0;
                }
            } else if (indexesOfFirstCharOfThePair[1] == indexesOfSecondCharOfThePair[1]) {
                if (indexesOfFirstCharOfThePair[0] < 4) {
                    indexesOfFirstCharOfThePair[0]++;
                } else {
                    indexesOfFirstCharOfThePair[0] = 0;
                }
                if (indexesOfSecondCharOfThePair[0] < 4) {
                    indexesOfSecondCharOfThePair[0]++;
                } else {
                    indexesOfSecondCharOfThePair[0] = 0;
                }
            } else {
                int temp = indexesOfFirstCharOfThePair[1];
                indexesOfFirstCharOfThePair[1] = indexesOfSecondCharOfThePair[1];
                indexesOfSecondCharOfThePair[1] = temp;
            }

            cipher = cipher + table[indexesOfFirstCharOfThePair[0]][indexesOfFirstCharOfThePair[1]]
                    + table[indexesOfSecondCharOfThePair[0]][indexesOfSecondCharOfThePair[1]];
        }
        return cipher;
    }

    public static String editingPlainText(String plainText) {
        int i = 0;
        int size = 0;
        String newPlainText = new String();
        size = plainText.length();
        for (int tmp = 0; tmp < size; tmp++) {
            if (plainText.charAt(tmp) == 'J') {
                newPlainText = newPlainText + 'I';
            } else {
                newPlainText = newPlainText + plainText.charAt(tmp);
            }
        }

        for (i = 1; i < newPlainText.length(); i = i + 2) {

            if ((newPlainText.charAt(i) == newPlainText.charAt(i - 1)) && (newPlainText.charAt(i) != 'X')
                    && (newPlainText.charAt(i - 1) != 'X')) {
                newPlainText = newPlainText.substring(0, i) + 'X' + newPlainText.substring(i);
            } else if ((newPlainText.charAt(i) == newPlainText.charAt(i - 1))
                    && (newPlainText.charAt(i) == 'X') && (newPlainText.charAt(i - 1) == 'X')) {
                newPlainText = newPlainText.substring(0, i) + 'V' + newPlainText.substring(i);

            }
        }
        int newSize = newPlainText.length();
        if (newPlainText.length() % 2 != 0 && newPlainText.charAt(newSize - 1) == 'X') {
            newPlainText = newPlainText + 'V';

        } else if (newPlainText.length() % 2 != 0 && newPlainText.charAt(newSize - 1) != 'X') {
            newPlainText = newPlainText + 'X';
        }
        return newPlainText;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String key = " ";
        String plainText = " ";

        key = scan.next();
        //  System.out.println(checkKey(key));
        plainText = scan.next();

        charactersMatrixForm(key);

        String cipher = playFairEncryption(plainText);
        System.out.println(cipher);

    }

}
