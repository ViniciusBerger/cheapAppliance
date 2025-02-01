package appliances;

import java.math.BigDecimal;

/**
 * Super class for all appliance items
 */
public class Appliance {
    public String itemNumber;
    public String brand;
    public Integer quantity;
    public Integer wattage;
    public String color;
    public BigDecimal price;
    String item_type;

    public Appliance(String itemNumber, String brand, int quantity, int wattage, String colour, double price, String item_type) {
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.color = colour;
        this.price = new BigDecimal(price);
    }


    public String getItemNumber() {
        return itemNumber;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getWattage() {
        return wattage;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[itemNumber= " + itemNumber + ", brand= " + brand + ", quantity= " + quantity + ", wattage ="
                + wattage + ", color= " + color + ", price= " + price + "]";
    }

    public String formatForFile()
    {
        return itemNumber + ";" + brand + ";" + quantity + ";" + wattage + ";" + color + ";" + price;
    }

}


