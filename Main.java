/**
 * @By KubIn
 */

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final static int DOLNA_GRANICA = 2;
    private final static int GORNA_GRANICA = 1000000;

    private static Scanner scanner;

    public static void otworzPlik() {
        try {
            scanner = new Scanner(new File("src/odczyt.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie moge znalesc pliku pod ta sciezka");
        }
    }

    public static void rozwiaz() {
        int liczbaLiter = scanner.nextInt();
        String jasNazwisko = scanner.next();
        String malgosiaNazwisko = scanner.next();

        if(liczbaLiterNieprawidlowa(liczbaLiter)){
            throw new IllegalArgumentException("Liczba liter nie miesci sie w warunku: 2<=n<=1000000");
        }
        if(dlugoscNazwiskaNieprawidolowa(liczbaLiter, jasNazwisko)){
            throw new IllegalArgumentException("Nazwsisko Jasia jest  dluzsze lub krotsze od liczby dozwolonych liter w nim");
        }
        if(dlugoscNazwiskaNieprawidolowa(liczbaLiter, malgosiaNazwisko)){
            throw new IllegalArgumentException("Nazwsisko Malgosi jest  dluzsze lub krotsze od liczby dozwolonych liter w nim");
        }
        if(takieSameLiteryWNazwiskach(jasNazwisko, malgosiaNazwisko)) {
            throw new IllegalArgumentException("Nazwisko Jasia i Malgosi nie uzywa tych samych liter");
        }

        while(!(jasNazwisko==malgosiaNazwisko)) {

        }


    }

    private static boolean takieSameLiteryWNazwiskach(String jasNazwisko, String malgosiaNazwisko) {
        char[] pierwsze = jasNazwisko.toCharArray();
        char[] drugie = malgosiaNazwisko.toCharArray();
        Arrays.sort(pierwsze);
        Arrays.sort(drugie);
        if(Arrays.equals(pierwsze, drugie)){
            return false;
        } else {
            return true;
        }
    }

    private static boolean liczbaLiterNieprawidlowa(int liczbaLiter) {
        return (liczbaLiter < DOLNA_GRANICA || liczbaLiter > GORNA_GRANICA);
    }

    private static boolean dlugoscNazwiskaNieprawidolowa(int liczbaLiter, String nazwisko) {
        if(liczbaLiter>nazwisko.length() || liczbaLiter<nazwisko.length()) {
            return true;
        }else if(liczbaLiter==nazwisko.length()) {
            return false;
        } else {
            throw new IllegalArgumentException("Ktores z nazwisk nie jest zapisane literami.");
        }
    }


    /**
     *
     *   PUSZCZANIE NIZEJ, JEST DOBRZE
     *
     */


    public static void main(String args[]) {
        otworzPlik();
        rozwiaz();
    }

}
