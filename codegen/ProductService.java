package com.abc.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.abc.core.exception.BusinessException;
import com.abc.entity.Product;
import com.abc.entity.ProductExample;

public interface ProductService {
	public void insertSelective(Product product) throws BusinessException;
	public void updateByPrimaryKeySelective(Product product) throws BusinessException;
	public List<Product> list(ProductExample example,RowBounds bound) throws BusinessException;
	public int count(ProductExample example) throws BusinessException;
	public List<Product> list(ProductExample example) throws BusinessException;
	public void deleteByPrimaryKey(Integer id) throws BusinessException;
	public Product selectByPrimaryKey(Integer id) throws BusinessException;
}
