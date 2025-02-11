package com.nt.service;

public interface IPurchaseOrderService {

	public String purchase(String items[],double price[],String toEmails[])throws Exception;
}
