package vacationClasses;

/**
 * represents a request for vacation swapping.
 */
public class swapRequest {
    private String swapRequest_id;
    private String seller_side_vacation_id;
    private String seller_side_userName;
    private String buyer_side_vacation_id;
    private String buyer_side_userName;
    private String swapStatus;

    //constructor
    public swapRequest(String swapRequest_id,String one_side_vacation_id,String one_side_userName,
                       String two_side_vacation_id,String two_side_userName,String swapStatus){
        this.swapRequest_id = swapRequest_id;
        this.seller_side_vacation_id = one_side_vacation_id;
        this.seller_side_userName = one_side_userName;
        this.buyer_side_vacation_id = two_side_vacation_id;
        this.buyer_side_userName = two_side_userName;
        this.swapStatus = swapStatus;
    }

    //getters
    public String getSwapRequest_id() {
        return swapRequest_id;
    }

    public String getSeller_side_vacation_id() {
        return seller_side_vacation_id;
    }

    public String getSeller_side_userName() {
        return seller_side_userName;
    }

    public String getBuyer_side_vacation_id() {
        return buyer_side_vacation_id;
    }

    public String getBuyer_side_userName() {
        return buyer_side_userName;
    }

    public String getSwapStatus() {
        return swapStatus;
    }

    //setters
    public void setSwapRequest_id(String swapRequest_id) {
        this.swapRequest_id = swapRequest_id;
    }

    public void setSeller_side_vacation_id(String one_side_vacation_id) {
        this.seller_side_vacation_id = one_side_vacation_id;
    }

    public void setSeller_side_userName(String one_side_userName) {
        this.seller_side_userName = one_side_userName;
    }

    public void setBuyer_side_vacation_id(String two_side_vacation_id) {
        this.buyer_side_vacation_id = two_side_vacation_id;
    }

    public void setBuyer_side_userName(String two_side_userName) {
        this.buyer_side_userName = two_side_userName;
    }

    public void setSwapStatus(String status) {
        this.swapStatus = status;
    }
}
