import Exceptions.InvalidInputException;

import java.util.Scanner;

public class CheapApplianceApp {
    static private final Scanner sc = new Scanner(System.in);
    static private final ConnectionFactory cf = new ConnectionFactory();
    public static void main(String[] args) {
        System.out.println("||==========================================||");
        System.out.println("||      Welcome to Cheap Appliances!        ||");
        System.out.println("||==========================================||");
        System.out.println("||     1-List appliances                    ||");
        System.out.println("||     2-Search brand                       ||");
        System.out.println("||     3-Search item                        ||");
        System.out.println("||     4-Checkout item                      ||");
        System.out.println("||     5-Exit                               ||");
        System.out.println("||==========================================||");

        String option = sc.nextLine();
        try {
            int userChoice = Integer.parseInt(option);
            if (userChoice <=0 || userChoice > 5) {
                throw new Exception("Invalid option");
            }
            switch (userChoice)
            {
                case 1: // list all items stored into the database
                    Utilities.listItems(cf.getConnection());
                    break;
                case 2:
                    Utilities.searchBrand(cf.getConnection());
                    break;
                case 3:
                    Utilities.searchItem(cf.getConnection());
                    break;
                case 4:
                    Utilities.checkOut(cf.getConnection());
                    break;
                case 5:
                    return;

            }

        }
        catch (Exception e)
        {
            try{
                throw new InvalidInputException(e.getMessage());
            } catch (InvalidInputException ex)
            {
                main(args);
            }
        }
        recallMain();

    }

    private static void recallMain()
    {
        System.out.println();
        System.out.println();
        System.out.println("||=====================================================||");
        System.out.println("||          You will be redirected to menu             ||");
        System.out.println("|| Press any key or ENTER to go back to the main menu  ||");
        System.out.println("||=====================================================||");

        sc.nextLine();
        main(new String[] {""});
    }
}
