/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author keiranpatel
 */
public class Summary {
	Integer numberOfSales;
	Double totalPayment;
	String group;

	public Summary() {
	}

	public Summary(Integer numberOfSales, Double totalPayment, String group) {
		this.numberOfSales = numberOfSales;
		this.totalPayment = totalPayment;
		this.group = group;
	}

	public Integer getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(Integer numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Summary{" + "numberOfSales=" + numberOfSales + ", totalPayment=" + totalPayment + ", group=" + group + '}';
	}

	
}
