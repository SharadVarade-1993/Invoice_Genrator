import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public RideRepository rideRepository;

    @InjectMocks
    public InvoiceService  invoiceService;




   /* @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceService();
    }*/

    @Test//1
    public void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.NORMAL,distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test//2
    public void givenLessDistanceAndTime_ShouldReturnMinFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.NORMAL,distance,time);
        Assert.assertEquals(5,fare,0.0);

    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary(){
        Ride[] rides = {new Ride(InvoiceService.ServiceType.NORMAL,2.0, 5),
                        new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
                      };
        when(rideRepository.getRides(ArgumentMatchers.any())).thenReturn(rides);
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenRidesAndUserId_ShouldReturnInvoiceSummary(){
          String userId = "a@b.com";
        Ride[] rides = {new Ride(InvoiceService.ServiceType.NORMAL,2.0, 5),
                        new Ride(InvoiceService.ServiceType.NORMAL,0.1,1)
        };
        when(rideRepository.getRides("a@b.com")).thenReturn(rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenPremiumRideWithDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.PREMIUM,distance,time);
        Assert.assertEquals(60,fare,0.0);
    }

    @Test
    public void givenPremiumRideWithLessDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(InvoiceService.ServiceType.PREMIUM,distance,time);
        Assert.assertEquals(30,fare,0.0);
    }
}