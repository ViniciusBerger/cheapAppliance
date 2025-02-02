
import Exceptions.InvalidInputException;
import appliances.Appliance;

import java.sql.Connection;
import java.util.Scanner;
import java.util.Set;


public class Utilities
{

    static private final Scanner sc = new Scanner(System.in);

    public static void listItems(Connection conn)
    {
        Set<Appliance> applianceSet = new ApplianceDAO(conn).list();
        applianceSet.forEach(System.out::println);
    }

    public static void searchBrand(Connection conn) {
        System.out.println("||=====================================================================||");
        System.out.println("|| Remember the search is case sensitive. First letter must Upper case ||");
        System.out.println("|| Enter the brand:                                                    ||");
        System.out.println("||=====================================================================||");

        try {
            String brand = sc.nextLine();
            Set<Appliance> applianceSet = new ApplianceDAO(conn).searchBrand(brand);
            if (applianceSet.isEmpty()) {
                throw new InvalidInputException("The brand you searched is not available");
            }
        } catch (Exception e) { throw new RuntimeException();}
    }

    public static void searchItem(Connection conn)
    {
        System.out.println("||=====================================================================||");
        System.out.println("|| Enter the item number:                                              ||");
        System.out.println("||=====================================================================||");

        try{
            String itemNumber = sc.nextLine();
            Appliance appliance = new ApplianceDAO(conn).searchItem(itemNumber);
            System.out.println(appliance.toString());
            if (itemNumber.isEmpty()) { throw new InvalidInputException("The item you searched is not available"); }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void checkOut(Connection conn)
    {
        System.out.println("||=====================================================================||");
        System.out.println("|| Enter the item number:                                              ||");
        System.out.println("|| Enter the amount:                                                   ||");
        System.out.println("||=====================================================================||");

        try {
            String itemNumber = sc.nextLine();
            int amount = Integer.parseInt(sc.nextLine());
            if (itemNumber.isEmpty()) { throw new InvalidInputException("invalid input");}

            ApplianceDAO applianceDAO = new ApplianceDAO(conn);
            applianceDAO.updateQuantity(itemNumber, -(amount));

            System.out.println("The item was checked out successfully");

        } catch (InvalidInputException e) {throw new RuntimeException();}

    }
}
