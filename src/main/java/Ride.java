public class Ride {
    public InvoiceService.ServiceType serviceType;
    public final int time;
    public   double distance;

    public Ride(InvoiceService.ServiceType serviceType,double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.serviceType = serviceType;
    }
}
