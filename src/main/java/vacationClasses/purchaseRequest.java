package vacationClasses;

public class purchaseRequest {
    private String purchaseRequestID;
    private String flightID;
    private String supplierUserName;
    private String purchaserUserName;
    private String status;

    public purchaseRequest(String purchaseRequestID, String flightID, String supplierUserName, String purchaserUserName){
        this.purchaseRequestID = purchaseRequestID;
        this.flightID = flightID;
        this.supplierUserName = supplierUserName;
        this.purchaserUserName = purchaserUserName;
        this.status = "Waiting for approval";
    }


    public String getPurchaseRequestID() {
        return purchaseRequestID;
    }
    public void setPurchaseRequestID(String purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }
    public String getFlightID() {
        return flightID;
    }
    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSupplierUserName() {
        return supplierUserName;
    }
    public void setSupplierUserName(String supplierUserName) {
        this.supplierUserName = supplierUserName;
    }
    public String getPurchaserUserName() {
        return purchaserUserName;
    }
    public void setPurchaserUserName(String purchaserUserName) {
        this.purchaserUserName = purchaserUserName;
    }
}
