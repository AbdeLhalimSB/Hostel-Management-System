
package Model;

/**
 *
 * @author sahba
 */
public class Reservation {
    private int ID;
    private String From,To,Room,Reserver,Price;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String Room) {
        this.Room = Room;
    }

    public String getReserver() {
        return Reserver;
    }

    public void setReserver(String Reserver) {
        this.Reserver = Reserver;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
    
}
