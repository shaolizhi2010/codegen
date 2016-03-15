package com.codegen.service;

import org.apache.commons.lang3.StringUtils;

import com.codegen.Utils;
import com.codegen.base.AbstractGenerator;


public class ServiceGenerator extends AbstractGenerator{

	public void gen(String projectName, String moduleName, String srcPath) {

		try {

			String moduleNameCapital = Utils.captureFirst(moduleName);
			
			// 取    template
			String generatedCode = getFileContent("src/com/codegen/service/service.template");
			
			//  替换module名
			generatedCode = StringUtils.replace(generatedCode, "$projectName$", projectName);
		 	
		 	generatedCode = StringUtils.replace(generatedCode, "$moduleName$", moduleName);
		 	generatedCode = StringUtils.replace(generatedCode, "$ModuleName$", moduleNameCapital);
		 	
			saveFile(generatedCode, srcPath+"service\\" +moduleNameCapital+   "Service.java");
			
			// 取    template
			 generatedCode = getFileContent("src/com/codegen/service/serviceImpl.template");
			
			//  替换module名
			generatedCode = StringUtils.replace(generatedCode, "$projectName$", projectName);
		 	
		 	generatedCode = StringUtils.replace(generatedCode, "$moduleName$", moduleName);
		 	generatedCode = StringUtils.replace(generatedCode, "$ModuleName$", moduleNameCapital);
		 	
			saveFile(generatedCode, srcPath +"service\\impl\\"+moduleNameCapital+   "ServiceImpl.java");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {

		try {
			String moduleName = "product";
			
			new ServiceGenerator().gen("abc",moduleName, "C:\\Users\\lizhi\\Desktop\\temp\\code\\" );

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
