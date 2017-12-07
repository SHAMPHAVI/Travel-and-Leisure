package com.niit.talbackend.dao;

import java.io.Serializable;
import java.util.List;

import com.niit.talbackend.model.BillingAddress;

public interface BillingAddressDao extends Serializable{
	public boolean savebilladdrs(BillingAddress address);
	public boolean Updatebilladdrs(BillingAddress address);
	public boolean deletebilladdrs(BillingAddress address);
	public BillingAddress get(String id);
	public List<BillingAddress> listofAddresses();
public String getmaxbillingaddressid();
}
