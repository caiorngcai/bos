/**
 * 
 */
package com.cai.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import com.cai.bos.utils.PinYin4jUtils;

/**
 * @author crc
 *	@date 2017年11月4日 下午7:54:24
 */
public class TestRegion {
	@Test
	public void testPOI() throws FileNotFoundException, IOException
	{
		String filePath="H:\\学习\\java\\javaee\\8\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls";
		//获得代表xls文件的对象
		HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(new File(filePath)));
		//获得一个工作表对象，一个xls文件可以有多个工作表
		HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			for (Cell cell : row) {
				String value=cell.getStringCellValue();
				System.out.println(value+" ");
			}
		}
		
	}
	@Test
	public void testPinyin4J()
	{
		String province = "河北省";
		String city = "石家庄市";
		String district = "桥西区";
		province=province.substring(0,province.length()-1);
		city=city.substring(0,city.length()-1);
		district=district.substring(0, district.length()-1);
		String info=province+city+district;
		//获得首拼音的数组
		String[] headByString=PinYin4jUtils.getHeadByString(info);
		String shortcode=StringUtils.join(headByString);
		System.out.println(shortcode);
		String citycode=PinYin4jUtils.hanziToPinyin(city,"");
		System.out.println(citycode);
	}
}
