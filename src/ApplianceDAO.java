import appliances.Appliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ApplianceDAO {

    private final Connection connection;
    public ApplianceDAO(Connection conn)
    {
        this.connection = conn;
    }

    public void save(Appliance appliance)
    {
        String sql = "INSERT INTO appliance (item_number, brand, quantity, wattage, colour, price) VALUES (?,?,?,?,?,?)";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appliance.getItemNumber());
            ps.setString(2, appliance.getBrand());
            ps.setInt(3, appliance.getQuantity());
            ps.setInt(4, appliance.getWattage());
            ps.setString(5, appliance.getColor());
            ps.setBigDecimal(6, appliance.getPrice());
            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Set<Appliance> list()
    {
        Set<Appliance> applianceSet = new HashSet<>();
        String sql = "SELECT * FROM appliance";

        try
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String itemNumber = rs.getString("item_number");
                String brand = rs.getString("brand");
                int quantity = rs.getInt("quantity");
                int wattage = rs.getInt("wattage");
                String colour = rs.getString("colour");
                double price = rs.getDouble("price");
                String appliance_type = rs.getString("appliance_type");
                applianceSet.add(new Appliance(itemNumber, brand, quantity, wattage, colour, price, appliance_type));
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return applianceSet;
    }

    public Set<Appliance> searchBrand(String brand)
    {
        Set<Appliance> applianceSet = new HashSet<>();
        String sql = "SELECT * FROM appliance WHERE brand = (?)";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, brand);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                applianceSet.add(new Appliance(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }

            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return applianceSet;
    }

    public Appliance searchItem(String itemNumber)
    {
        Appliance appliance = null;
        String sql = "SELECT * FROM appliance WHERE item_number = (?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, itemNumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String brand = rs.getString("brand");
                int quantity = rs.getInt("quantity");
                int wattage = rs.getInt("wattage");
                String colour = rs.getString("colour");
                double price = rs.getDouble("price");
                String item_type = rs.getString("appliance_type");

                appliance = new Appliance(itemNumber, brand, quantity, wattage, colour, price, item_type);
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appliance;
    }

    public void updateQuantity(String itemNumber, int quantity)
    {
        String sql = "UPDATE appliance SET quantity = quantity + (?) WHERE item_number = (?)";
        try{
            connection.prepareStatement(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setString(2, itemNumber);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
