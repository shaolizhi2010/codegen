package com.codegen;


import com.codegen.controller.ControllerGenerator;
import com.codegen.service.ServiceGenerator;
import com.codegen.sqlmap.SqlMapGenerator;

public class CodeGenerator {

	public static void main(String[] args) {

		try {
			
			
			String projectName = "liker";
			String srcpath = "D:\\devall\\workspace\\workspace66\\liker\\src\\com\\liker\\";
			String sqlMapPath = "D:\\devall\\workspace\\workspace66\\liker\\resources\\mybatis\\sqlmap\\";
			String moduleName = "productPrice"; //首字母小写 productPrice productAttr
			String dbName = "product_price";
			
			new SqlMapGenerator()
				.gen(projectName,moduleName,srcpath,sqlMapPath,dbName);
			
			new ServiceGenerator()
				.gen(projectName,moduleName,srcpath);
			
			new ControllerGenerator()
				.gen(projectName,moduleName,srcpath);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 

}
