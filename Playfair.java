import java.util.*;
import java.io.*;

public class Playfair {
    private char pfmatrix[][] = new char[5][5];
    public String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int xpad = 0;
    int row, col;

    public void matrixgen(String key) {
        char keychar;
        int count = 0;
        int alphacount = 0;
        int p, k, flg = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (count < key.length()) {
                    keychar = key.charAt(count);
                    if (keychar == 'J') keychar = 'I';
                    p = 0;
                    while (p < count) {
                        flg = 1;
                        if (keychar == key.charAt(p)) {
                            count++;
                            if (count == key.length()) {
                                flg = 0;
                                break;
                            }
                            keychar = key.charAt(count);
                            p = 0;
                        } else p++;
                    }
                    if (flg != 0) {
                        pfmatrix[i][j] = keychar;
                        count++;
                    }
                    if ((count == key.length()) && (flg == 0)) {
                        if (alphacount < 26) {
                            keychar = ALPHABET.charAt(alphacount);
                            k = 0;
                            while (k < key.length()) {
                                if ((keychar == key.charAt(k)) || (keychar == 'J')) {
                                    alphacount++;
                                    keychar = ALPHABET.charAt(alphacount);
                                    k = 0;
                                } else k++;
                            }
                            if (keychar != 'J' && k == key.length()) pfmatrix[i][j] = keychar;
                            alphacount++;
                        }
                    }
                } else {
                    if (alphacount < 26) {
                        keychar = ALPHABET.charAt(alphacount);
                        k = 0;
                        while (k < key.length()) {
                            if ((keychar == key.charAt(k)) || (keychar == 'J')) {
                                alphacount++;
                                keychar = ALPHABET.charAt(alphacount);
                                k = 0;
                            } else k++;
                        }
                        pfmatrix[i][j] = keychar;
                        alphacount++;
                    }
                }
            }
        }
    }

    public void matrixdisplay() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(pfmatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String pfencryption(String txt) {
        int i, k;
        int ch1row, ch2row, ch1col, ch2col;
        char ch1, ch2, tmp1, tmp2;
        String nutext = "";
        String text = "";
        i = 0;

        txt = txt.replace(" ", "").toUpperCase();

        while (i < (txt.length() - 1)) {
            text += txt.charAt(i);
            if (txt.charAt(i) == txt.charAt(i + 1)) {
                text += 'X';
                xpad++;
            }
            i++;
        }
        text += txt.charAt(txt.length() - 1);
        System.out.println("TEXT: " + text);

        if (text.length() % 2 != 0) {
            text += 'X';
            xpad++;
        }
        System.out.println("FINAL TEXT: " + text);

        for (k = 0; k < text.length(); k = k + 2) {
            ch1 = text.charAt(k);
            ch2 = text.charAt(k + 1);
            matsearch(ch1);
            ch1row = row;
            ch1col = col;
            matsearch(ch2);
            ch2row = row;
            ch2col = col;

            if (ch1row == ch2row) { // Same Row Rule [cite: 30]
                tmp1 = pfmatrix[ch1row][(ch1col + 1) % 5];
                tmp2 = pfmatrix[ch2row][(ch2col + 1) % 5];
            } else if (ch1col == ch2col) { // Same Column Rule [cite: 31]
                tmp1 = pfmatrix[(ch1row + 1) % 5][ch1col];
                tmp2 = pfmatrix[(ch2row + 1) % 5][ch2col];
            } else { // Rectangle Rule
                tmp1 = pfmatrix[ch1row][ch2col];
                tmp2 = pfmatrix[ch2row][ch1col];
            }
            nutext += tmp1;
            nutext += tmp2;
        }
        return nutext;
    }

    public void matsearch(char ch) {
        if (ch == 'J') ch = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pfmatrix[i][j] == ch) {
                    row = i;
                    col = j;
                }
            }
        }
    }

    public static void main(String[] args) {
        Playfair pf = new Playfair();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the PLAYFAIR KEY: ");
        String pfkey = sc.nextLine();

        pf.matrixgen(pfkey);
        pf.matrixdisplay();

        System.out.println("Enter PLAIN TEXT: ");
        String ptext = sc.nextLine();

        String ctext = pf.pfencryption(ptext);
        System.out.println("\nCIPHER TEXT: " + ctext);
        sc.close();
    }
}