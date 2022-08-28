
package Model;

/**
 *
 * @author sahba
 */
public class Room {
    private int ID;
    private String Name,Price,Position,Beds,MP;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getBeds() {
        return Beds;
    }

    public void setBeds(String Beds) {
        this.Beds = Beds;
    }

    public String getMP() {
        return MP;
    }

    public void setMP(String MP) {
        this.MP = MP;
    }
    
}
