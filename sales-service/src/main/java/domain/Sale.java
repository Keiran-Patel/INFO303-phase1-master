/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author keiranpatel
 */
public class Sale {

    private String id;
    private String saleDate;
    private Customer customer;
    private Totals totals;
    private Collection<SaleItem> items = new ArrayList<SaleItem>();

    public Sale() {
    }

    public Sale(String id, String saleDate, Customer customer, Totals totals, SaleItem saleItem) {
        this.id = id;
        this.saleDate = saleDate;
        this.customer = customer;
        this.totals = totals;
        this.items.add(saleItem);
    }

    public Sale(String id, String saleDate, Customer customer, Totals totals) {
        this.id = id;
        this.saleDate = saleDate;
        this.customer = customer;
        this.totals = totals;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }

    public Collection<SaleItem> getSaleItem() {
        return items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addSaleItem(SaleItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        //return "Sale{" + "id=" + id + ", saleDate=" + saleDate + ", customer=" + customer + totals=" + totals +'}';"
        return "Sale{" + "id=" + id + ", saleDate=" + saleDate + ", customer=" + customer + ", items=" + items + ", totals=" + totals + '}';

    }
}
