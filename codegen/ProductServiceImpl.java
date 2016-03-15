package com.abc.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.core.exception.BusinessException;
import com.abc.entity.Product;
import com.abc.entity.ProductExample;
import com.abc.mapper.ProductMapper;
import com.abc.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService { 

	@Autowired
	private ProductMapper productMapper;
 
	private   static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Override
	public void insertSelective(Product product) throws BusinessException{
		productMapper.insertSelective(product);
	}
	
	@Override
	public void deleteByPrimaryKey(Integer id) throws BusinessException{
		productMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateByPrimaryKeySelective(Product product) throws BusinessException{
		productMapper.updateByPrimaryKeySelective(product);
	}

	@Override
	public List<Product> list(ProductExample example,RowBounds bound) throws BusinessException {
		return productMapper.selectPagination(example, bound);
	}
	
	@Override
	public List<Product> list(ProductExample example) throws BusinessException {
		return productMapper.selectByExample(example);
	}

	@Override
	public int count(ProductExample example) throws BusinessException  {
		return productMapper.countByExample(example);
	}

	@Override
	public Product selectByPrimaryKey(Integer id) throws BusinessException {
		return productMapper.selectByPrimaryKey(id);
	}

	
}
