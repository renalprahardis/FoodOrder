package id.project.renal.Model;

/**
 * Created by ASUS on 28/02/2018.
 */

public class Menu {
    private String Name, Image, Image2, Description, Price, Discount, MenuId;

    public Menu() {
    }

    public Menu(String name, String image, String image2, String description, String price, String discount, String menuId) {
        Name = name;
        Image = image;
        Image2 = image2;
        Description = description;
        Price = price;
        Discount = discount;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }
    public String getImage2() {
        return Image2;
    }


    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
