package com.codegen;

import com.codegen.controller.ControllerGenerator;
import com.codegen.service.ServiceGenerator;

public class CodeGenerator {

	public static void main(String[] args) {

		try {
			String projectName = "liker";
			String moduleName = "product";
			
			new ServiceGenerator()
				.gen(projectName,moduleName, "C:\\Users\\lizhi\\Desktop\\temp\\code\\" );
			
			new ControllerGenerator()
				.gen(projectName,moduleName,"C:\\Users\\lizhi\\Desktop\\temp\\code\\");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 

}
