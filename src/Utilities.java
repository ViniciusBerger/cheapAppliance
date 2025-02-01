
import Exceptions.InvalidInputException;
import appliances.Appliance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


public class Utilities
{

    static private Scanner sc = new Scanner(System.in);

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
        } catch (Exception e) {

        }


    }

}
