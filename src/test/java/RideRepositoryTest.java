import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {



    @Test
    public void givenUser_whenFound_shouldReturnRideList(){
        RideRepository rideRepository = new RideRepository();
        Ride[] rides = {new Ride(InvoiceService.ServiceType.NORMAL,2.0, 5),
                new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
        };
        rideRepository.addRide("abc@gmail.com",rides);
        Assert.assertEquals(rideRepository.getRides("abc@gmail.com").length,rides.length);
    }
}
