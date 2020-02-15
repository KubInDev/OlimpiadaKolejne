/**
 * @By KubIn
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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

        System.out.println("Nazwisko Jasia: " + jasNazwisko);
        System.out.println("Nazwisko Malgosi: " + malgosiaNazwisko);
        System.out.println("------------");
        char[] literyWNazwiskuJasia = new char[liczbaLiter];
        literyWNazwiskuJasia = jasNazwisko.toCharArray();
        char[] literyWNazwiskuMalgosi = new char[liczbaLiter];
        literyWNazwiskuMalgosi = malgosiaNazwisko.toCharArray();
        char[] doNaprawieniaLitery = new char[liczbaLiter];
        char[] dobreLitery = new char[liczbaLiter];
        int przelozenia = 0;


        while(!(Arrays.equals(literyWNazwiskuJasia, literyWNazwiskuMalgosi))) {
            for (int x = 0; liczbaLiter - 1 >= x; x++) {

                if(!(literyWNazwiskuJasia[x]==literyWNazwiskuMalgosi[x])) {
                    int miejsceLiteryUJasia = x+1;
                    doNaprawieniaLitery[x]=literyWNazwiskuJasia[x];
                    System.out.println(doNaprawieniaLitery[x] + "; Ta litera jest na miejscu: " + miejsceLiteryUJasia);
                    for(int z=0; z<=liczbaLiter - 1; z++) {
                        if(doNaprawieniaLitery[x]==literyWNazwiskuMalgosi[z]) {
                            if(!(literyWNazwiskuMalgosi[z]==literyWNazwiskuJasia[z])) {
                                int miejsceLiteryUMalgosi = z+1;
                                System.out.println("Litera odpowiadająca jest na: " + miejsceLiteryUMalgosi + " miejscu.");
                                while(!(literyWNazwiskuMalgosi[miejsceLiteryUMalgosi-1]==literyWNazwiskuJasia[miejsceLiteryUMalgosi-1])) {
                                    if(czyWPrawo(miejsceLiteryUJasia, miejsceLiteryUMalgosi)) {
                                        int oIleWPrawo = miejsceLiteryUMalgosi-miejsceLiteryUJasia;
                                        for(int p=1; p<=oIleWPrawo; p++ ) {
                                            literyWNazwiskuJasia=swap(jasNazwisko, p-1, p);
                                            przelozenia++;
                                        }
                                    } else {
                                        int oIleWLewo = miejsceLiteryUJasia-miejsceLiteryUMalgosi;
                                        for(int p=1; p<=oIleWLewo; p++) {
                                            literyWNazwiskuJasia=swap(jasNazwisko, p, p-1);
                                            przelozenia++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    dobreLitery[x] = literyWNazwiskuJasia[x];
                }

            }
        }
        System.out.println("Ulozone w " + przelozenia);
        for (int x = 0; liczbaLiter - 1 >= x; x++) {
            System.out.println(literyWNazwiskuJasia[x]);
        }
    }


    static char[] swap(String str, int i, int j) {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return ch;
    }

    private static boolean czyWPrawo(int miejsceLiteruUJasia, int miejsceLiteryUMalgosi) {
        return miejsceLiteruUJasia < miejsceLiteryUMalgosi;
    }

    private static boolean takieSameLiteryWNazwiskach(String jasNazwisko, String malgosiaNazwisko) {
        char[] pierwsze = jasNazwisko.toCharArray();
        char[] drugie = malgosiaNazwisko.toCharArray();
        Arrays.sort(pierwsze);
        Arrays.sort(drugie);
        return !Arrays.equals(pierwsze, drugie);
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
