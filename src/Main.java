import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Szkeleton s = new Szkeleton();
        System.out.println("Please enter the use case number you wanna test:");
        Scanner scanner = new Scanner(System.in);
        int useCase = scanner.nextInt();
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
        }
    }
}