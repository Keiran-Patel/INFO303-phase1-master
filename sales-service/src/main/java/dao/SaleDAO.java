package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Customer;
import domain.Sale;
import domain.Summary;
import domain.SaleItem;
import domain.Totals;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SaleDAO {

    private static final Multimap<String, Sale> salesByCustomer = ArrayListMultimap.create();
    private static final Map<String, Sale> sales = new HashMap<>();

    private static final Double THRESHHOLD = 5000.0;

    /*
	 * Some dummy data for testing
     */
    static {
        if (sales.isEmpty()) {
            sales.put("WD1234", new Sale("WD12345", "1", new Customer("567", "keiran", "patel", "111", "@gmial.com", "VIP"), new Totals(1.0, 0.5), new SaleItem("1234", 5.0, 3.0)));
            salesByCustomer.put("567", new Sale("WD1234", "1", new Customer("567", "keiran", "patel", "111", "@gmial.com", "VIP"), new Totals(1.0, 0.5), new SaleItem("1234", 5.0, 3.0)));
            salesByCustomer.put("123", new Sale("WD1234", "1", new Customer("123", "leon", "patel", "111", "@gmial.com", "VIP"), new Totals(1.0, 0.5), new SaleItem("1234", 5.0, 3.0)));
            sales.put("WD1234", new Sale("WD1234", "2", new Customer("123", "leon", "patel", "111", "@gmial.com", "VIP"), new Totals(1.0, 0.5), new SaleItem("1234", 5.0, 3.0)));
        }
    }

    public void save(Sale sale) {
        salesByCustomer.put(sale.getCustomer().getId(), sale);
        sales.put(sale.getId(), sale);
    }

    public void remove(String saleId) {
        Sale sale = sales.get(saleId);
        salesByCustomer.remove(sale.getCustomer().getId(), sale);
        sales.remove(sale.getId());
    }

    public Collection<Sale> getSales() {
        return sales.values();
    }

    public Sale getSalesBysaleId(String saleId) {
        return sales.get(saleId);
    }

    public Collection<Sale> getSalesBycustomerId(String customerId) {
        return salesByCustomer.get(customerId);
    }

    public Boolean doesSaleExist(String saleId) {
        return sales.containsKey(saleId);
    }

    public Boolean doesCustomerExist(String customerId) {
        return salesByCustomer.containsKey(customerId);
    }

    public Summary getSummary(String customerId) {
        Collection<Sale> custSales = getSalesBycustomerId(customerId);

        Summary summary = new Summary();
        summary.setNumberOfSales(custSales.size());
        Double totalPayment = custSales.stream().mapToDouble(sale -> sale.getTotals().getTotalPayment()).sum();
        summary.setTotalPayment(totalPayment);
        summary.setGroup(totalPayment <= THRESHHOLD ? "Regular Customers" : "VIP Customers");

        return summary;
    }

}
