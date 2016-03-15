package com.abc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.core.exception.BusinessException;
import com.abc.entity.Product;
import com.abc.entity.ProductExample;


@RestController
public class ProductController {

	private static Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/create")
	public void create(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			productService.insert(product);
			MVCUtil.responseJsonOfSuccess(response, "操作成功");
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/edit")
	public void edit(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			productService.updateByPrimaryKeySelective(product);
			MVCUtil.responseJsonOfSuccess(response, "操作成功");
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			productService.deleteByPrimaryKey(product.getId());
			MVCUtil.responseJsonOfSuccess(response, "操作成功");
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			int limit = NumberUtils.toInt(request.getParameter("_limit"));
			int start=(NumberUtils.toInt(request.getParameter("currentPage"))-1)*Constants.PAGESIZE;
			List<Product> products;
			ProductExample example = new ProductExample();
			ProductExample.Criteria query = example.createCriteria();
			
			products = productService.list(example, new RowBounds(start, limit));
			MVCUtil.responseJsonOfSuccess(response, products);
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
		
	}
	 
	
	/**
	 * 详细
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			Product product = productService.selectByPrimaryKey(product.getId());
			MVCUtil.responseJsonOfSuccess(response, product);
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
		
	}
	
	/**
	 * 总数
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/count")
	public void count(HttpServletRequest request, HttpServletResponse response,Product product) {
		try {
			ProductExample example = new ProductExample();
			ProductExample.Criteria query = example.createCriteria();
			
			int count = productService.count(example);
		
			MVCUtil.responseJsonOfSuccess(response, count);
		} catch (BusinessException e) {
			MVCUtil.responseJsonOfError(response, e.getMessage());
		} catch (Exception e) {
			MVCUtil.responseJsonOfError(response,e.getMessage());
			logger.warn(e);
			logger.warn("Request Parameters : "+ JsonUtil.toJson(request.getParameterMap() ));
		}
	}
}
