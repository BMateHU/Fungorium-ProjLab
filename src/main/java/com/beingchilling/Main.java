package com.beingchilling;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Szkeleton s = new Szkeleton();
        Scanner scanner = new Scanner(System.in);
        do {
            printMenu();
            int useCase = scanner.nextInt();
            System.out.println("--------------------------------------------------------");
            switch (useCase) {
                case 1:
                    s.useCase1();
                    break;
                case 2:
                    s.useCase2();
                    break;
                case 3:
                    s.useCase3();
                    break;
                case 4:
                    s.useCase4();
                    break;
                case 5:
                    s.useCase5();
                    break;
                case 6:
                    s.useCase6();
                    break;
                case 7:
                    s.useCase7();
                    break;
                case 8:
                    s.useCase8();
                    break;
                case 9:
                    s.useCase9ab();
                    System.out.println(" ");
                    s.useCase9c();
                    break;
                case 10:
                    s.useCase10();
                    break;
                case 11:
                    s.useCase11();
                    break;
                case 12:
                    s.useCase12();
                    break;
                case 13:
                    s.useCase13();
                    break;
                case 14:
                    s.useCase14();
                    break;
                case 15:
                    s.useCase15();
                    break;
                case 16:
                    s.useCase16();
                    break;
                case 17:
                    s.useCase17();
                    break;
                case 18:
                    s.useCase18();
                    break;
                case 19:
                    s.useCase19();
                    break;
                case 20:
                    s.useCase20();
                    break;
                case 21:
                    s.useCase21();
                    break;
                default:
                    exit(0);
            }
            System.out.println("--------------------------------------------------------");
        } while(true);
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