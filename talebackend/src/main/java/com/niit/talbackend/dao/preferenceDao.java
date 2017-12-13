package com.niit.talbackend.dao;

import java.io.Serializable;
import java.util.List;

import com.niit.talbackend.model.preference;

public interface preferenceDao extends Serializable{
	public boolean saveshipaddrs(preference address);
	public boolean Updateshipaddrs(preference address);
	public boolean deleteshipaddrs(preference address);
	public preference get(String id);
	public List<preference> listofAddresses();
public String getpreferenceid();
}
