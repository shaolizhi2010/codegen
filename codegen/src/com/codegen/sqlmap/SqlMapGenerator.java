package com.codegen.sqlmap;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.codegen.Utils;
import com.codegen.base.AbstractGenerator;
 
 

public class SqlMapGenerator extends AbstractGenerator{

	public void gen(String projectName,String moduleName, String srcPath, String sqlMapPath, String dbName) {

		try {
			String moduleNameCapital = Utils.captureFirst(moduleName);
			
			// 取    template
			String generatedCode = getFileContent("src/com/codegen/sqlmap/selectPage.template");
			
			//  替换module名
			generatedCode = StringUtils.replace(generatedCode, "$projectName$", projectName);
			 
		 	generatedCode = StringUtils.replace(generatedCode, "$moduleName$", moduleName);
		 	generatedCode = StringUtils.replace(generatedCode, "$ModuleName$", moduleNameCapital);
		 	generatedCode = StringUtils.replace(generatedCode, "$dbName$", dbName);
		 	
			
		 	//处理 sqlmap.xml
		 	String sqlmapXml = getFileContent(sqlMapPath+moduleNameCapital+"Mapper.xml");
		 	if(!StringUtils.contains(sqlmapXml, "selectPage")){
		 		sqlmapXml = StringUtils.replaceOnce(sqlmapXml,"</mapper>",generatedCode+"</mapper>");
			 	
				saveFile(sqlmapXml, sqlMapPath+moduleNameCapital+"Mapper.xml");
		 	}
		 	
		 	//处理 sqlmapConfig.xml
		 	String xmlConfig = "<mapper resource=\"mybatis/sqlmap/"+moduleNameCapital+"Mapper.xml\"/>";
		 	String sqlmapConfigXml = getFileContent( new File(sqlMapPath).getParent()+"\\sqlMapConfig.xml"  );
		 	if(!StringUtils.contains(sqlmapConfigXml, moduleNameCapital+"Mapper")){
		 		sqlmapConfigXml = StringUtils.replaceOnce(sqlmapConfigXml,"</mappers>",xmlConfig+"</mappers>");
			 	
				saveFile(sqlmapConfigXml, new File(sqlMapPath).getParent()+"\\sqlMapConfig.xml");
		 	}
		 	
			//处理 sqlmap.java

			String sqlmapJava =   getFileContent(srcPath+"mapper//"+moduleNameCapital+"Mapper.java");
		 	if(!StringUtils.contains(sqlmapJava, "selectPage")){
				String importCode = "import org.apache.ibatis.session.RowBounds;";
				String methodcode = " List<"+moduleNameCapital+"> selectPage("+moduleNameCapital+"Example example,RowBounds bounds); ";
				
				sqlmapJava = StringUtils.replaceOnce(sqlmapJava,"import",importCode+"\r\nimport");
				sqlmapJava = StringUtils.replaceOnce(sqlmapJava,"}","\r\n\t"+methodcode+"\r\n}");	
				
				saveFile(sqlmapJava, srcPath+"mapper//"+moduleNameCapital+"Mapper.java");
		 	}
		 	
		 	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		try {
			
			String moduleName = "productAttr";
			
			new SqlMapGenerator().gen("liker",moduleName,"D:\\devall\\workspace\\workspace66\\liker\\src\\com\\liker\\","D:\\devall\\workspace\\workspace66\\liker\\resources\\mybatis\\sqlmap\\","product_attr");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
