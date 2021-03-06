package com.niit.talbackend.dao;

import java.util.List;

import com.niit.talbackend.model.Category;
public interface CategoryDao {

	public String save(Category category);
	public String update(Category category);
	public boolean delete(Category category);
	public Category get(String id);
	public List<Category> list();
}
