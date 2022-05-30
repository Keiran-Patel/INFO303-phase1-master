
import api.SalesApi;
import api.SalesForCustomerApi;
import domain.Customer;
import domain.Sale;
import domain.SaleItem;
import domain.Summary;
import domain.Totals;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SalesIntegrationTest {

    private SalesApi salesapi;
    private SalesForCustomerApi salesforcustomerapi;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8081/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private SalesIntegrationTest salesIntegrationTest;

    private Customer customer1;
    private Customer customer2;
    private Sale sale1;
    private Sale sale2;
    private Sale sale3;
    private Sale sale4;

    @BeforeEach
    public void setUp() throws IOException {
        salesapi = retrofit.create(SalesApi.class);
        salesforcustomerapi = retrofit.create(SalesForCustomerApi.class);

        customer1 = new Customer();
        customer1.setId("customer1_id");
        customer1.setFirstName("customer1_firstname");
        customer1.setLastName("customer1_lastname");
        customer1.setGroup("regular");
        customer1.setEmail("customer1@gmail.com");
        customer1.setCustomerCode("12345");

        customer2 = new Customer();
        customer2.setId("customer2_id");
        customer2.setFirstName("customer2_firstname");
        customer2.setLastName("customer2_lastname");
        customer2.setGroup("regular");
        customer2.setEmail("customer2@gmail.com");
        customer2.setCustomerCode("123");

        sale1 = new Sale();
        sale1.setId("1");
        sale1.setSaleDate("today");
        sale1.setCustomer(customer1);
        List<SaleItem> saleitems = new ArrayList<SaleItem>();
        saleitems.add(new SaleItem().price(100.00).productId("12345").quantity(1.0f));
        sale1.setItems(saleitems);
        Totals totals = new Totals();
        totals.totalPayment(100.00);
        totals.totalTax(0.0);
        totals.totalPrice(100.00);
        sale1.setTotals(totals);

        sale2 = new Sale();
        sale2.setId("2");
        sale2.setSaleDate("today");
        sale2.setCustomer(customer1);
        saleitems.add(new SaleItem().price(6000.00).productId("6789").quantity(1.0f));
        sale2.setItems(saleitems);
        totals = new Totals();
        totals.totalPayment(6000.00);
        totals.totalTax(0.0);
        totals.totalPrice(6000.00);
        sale2.setTotals(totals);

        sale3 = new Sale();
        sale3.setId("3");
        sale3.setSaleDate("today");
        sale3.setCustomer(customer2);
        saleitems.add(new SaleItem().price(100.00).productId("12345").quantity(1.0f));
        sale3.setItems(saleitems);
        totals = new Totals();
        totals.totalPayment(100.00);
        totals.totalTax(0.0);
        totals.totalPrice(100.00);
        sale3.setTotals(totals);

        sale4 = new Sale();
        sale4.setId("4");
        sale4.setSaleDate("today");
        sale4.setCustomer(customer1);
        saleitems.add(new SaleItem().price(100.00).productId("12345").quantity(1.0f));
        sale4.setItems(saleitems);
        totals = new Totals();
        totals.totalPayment(100.00);
        totals.totalTax(0.0);
        totals.totalPrice(100.00);
        sale4.setTotals(totals);

        salesapi.addNewSale(sale1).execute();
        salesapi.addNewSale(sale2).execute();
        salesapi.addNewSale(sale3).execute();
    }

    @AfterEach
    public void cleanUp() throws IOException {

        salesapi.deleteSale(sale1.getId()).execute();
        salesapi.deleteSale(sale2.getId()).execute();
        salesapi.deleteSale(sale3.getId()).execute();
        salesapi.deleteSale(sale4.getId()).execute();

    }

    @Test
    public void testCreateSale() throws IOException {

        // call the method being tested
        Response<Void> createResponse = salesapi.addNewSale(sale4).execute();

        // check for 201 response
        assertThat(createResponse.code(), is(201));

        // GET the sale that was created to check it was saved properly
        Response<Sale> getResponse = salesapi.getSpecificIDSale(sale4.getId()).execute();

        // get the product from the response body
        Sale returnesales = getResponse.body();

        // check that returned sale has the correct properties (except for URI which was set by the service, so will be different)
        assertThat(returnesales, samePropertyValuesAs(sale4, "uri"));

        // call the method again - should get 422 response this time
        createResponse = salesapi.addNewSale(sale4).execute();
        assertThat(createResponse.code(), is(422));

    }

    @Test
    public void testGetSales() throws IOException {

        // call the method being tested
        var getResponse = salesapi.getAllSales().execute();

        // get the sales from the response
        var returnedSales = getResponse.body();

        // check for 200 response
        assertThat(getResponse.code(), is(200));

        // check that response includes both sale1,sale2,sale3
        assertThat(returnedSales, hasItems(sale1, sale2, sale3));

    }

    @Test
    public void testDeleteSale() throws IOException {
        Response<Sale> Checksaleresponse = salesapi.getSpecificIDSale(sale1.getId()).execute();
        assertThat(Checksaleresponse.code(), is(200));
        // call the method being tested
        Response<Void> getResponse = salesapi.deleteSale(sale1.getId()).execute();
        assertThat(getResponse.code(), is(204));
        // get the sales from the response
        Checksaleresponse = salesapi.getSpecificIDSale(sale1.getId()).execute();
        assertThat(Checksaleresponse.code(), is(404));

    }

    @Test
    public void totalgetsummary() throws IOException {
        Response<Summary> Checksaleresponse = salesforcustomerapi.getCustomerSummary(customer1.getId()).execute();
        assertThat(Checksaleresponse.body().getNumberOfSales(), is(2));
        assertThat(Checksaleresponse.body().getTotalPayment(), is(new BigDecimal("6100.0")));
        assertThat(Checksaleresponse.body().getGroup(), is("VIP Customers"));
        assertThat(Checksaleresponse.code(), is(200));

    }

}
