package Exceptions;

import java.util.Scanner;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("||=====================================================||");
        System.out.println("||      Oh no!! Looks like something went wrong.       ||");
        System.out.println("||  Fail caused by: " + message);
        System.out.println("||          You will be redirected to menu             ||");
        System.out.println("||=====================================================||");

        System.out.println("Press any key or ENTER to go back to the main menu");
        sc.nextLine();


    }
}
