package com.beingchilling;

import com.beingchilling.model.Insect;
import com.beingchilling.model.MushroomBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static final int maxRound = 20;
    static boolean start = false;

    public static void main(String[] args) {
        printMenu();
        while(!start) {
            String command = sc.nextLine();
            switch (command) {
                case "start":
                    start = true;
                    break;
                case "init":
                    //loads map, gives them to Map.class and then gives it to view later at main game loop
                    break;
                case "exit":
                    exit(0);
                    break;
            }
        }
    }

    public static void printMenu()
    {
        System.out.println("1. Rovar mozgás effektus nélkül");
        System.out.println("2. Rovar mozgás lassító effektussal");
        System.out.println("3. Rovar mozgás gyorsító effektussal");
        System.out.println("4. Rovar mozgás bénító effektussal");
        System.out.println("5. Rovar mozgás sikertelen");
        System.out.println("6. Rovar vágás sikertelen");
        System.out.println("7. Rovar vágás");
        System.out.println("8. Gombatest növesztés");
        System.out.println("9. Gombatest növesztése sikertelen");
        System.out.println("10. Spóraszórás");
        System.out.println("11. Spóraszórás sikertelen");
        System.out.println("12. Gombafonal növesztése");
        System.out.println("13. Gombafonal növesztés sikertelen");
        System.out.println("14. Gombafonal gyorsítás");
        System.out.println("15. Tekton kettétörése");
        System.out.println("16. Tekton kettétörése sikertelen");
        System.out.println("17. Rovar Eszik (gyorsító Spóra)");
        System.out.println("18. Rovar Eszik (lassító Spóra)");
        System.out.println("19. Rovar Eszik (bénító Spóra)");
        System.out.println("20. Rovar Eszik (némító Spóra)");
        System.out.println("21. Rovar Eszik");
        System.out.println("0. Exit");
        System.out.println("Please enter the use case number you wanna test:");
    }
}