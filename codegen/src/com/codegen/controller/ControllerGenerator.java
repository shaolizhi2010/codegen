package com.codegen.controller;

import org.apache.commons.lang3.StringUtils;

import com.codegen.Utils;
import com.codegen.base.AbstractGenerator;
 

public class ControllerGenerator extends AbstractGenerator{

	public void gen(String projectName,String moduleName, String srcPath) {

		try {
			String moduleNameCapital = Utils.captureFirst(moduleName);
			
			// 取    template
			String generatedCode = getFileContent("src/com/codegen/controller/controller.template");
			
			//  替换module名
			generatedCode = StringUtils.replace(generatedCode, "$projectName$", projectName);
			 
		 	generatedCode = StringUtils.replace(generatedCode, "$moduleName$", moduleName);
		 	generatedCode = StringUtils.replace(generatedCode, "$ModuleName$", moduleNameCapital);
			
			saveFile(generatedCode, srcPath+"controller\\" +moduleNameCapital+   "Controller.java");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		try {
			
			String moduleName = "product";
			
			new ControllerGenerator().gen("abc",moduleName,"C:\\Users\\lizhi\\Desktop\\temp\\code\\");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
