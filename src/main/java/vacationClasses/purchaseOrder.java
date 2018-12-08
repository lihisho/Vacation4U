package vacationClasses;

import java.util.Date;

public class purchaseOrder {
    private User purchaser;
    private User supplier;
    private int price;
    private String orderID; // We can save an order id for apurchase prden, and another ID for the Vacation. Or onlyone of them.
    private String vacationID; //  Or flightID
    private String status; //?? waiting approval\sold\ sent\ accomplished \ done \ waiting for payment WHATEVER..
    private String comments;

    public purchaseOrder(){

    }


}
