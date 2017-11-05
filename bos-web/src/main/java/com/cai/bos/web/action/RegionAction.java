/**
 * 
 */
package com.cai.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Region;
import com.cai.bos.service.RegionService;
import com.cai.bos.utils.PinYin4jUtils;

/**
 * @author crc
 *	@date 2017年11月4日 下午7:14:55
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	//用来分区页面添加时查询区域需要接受的参数
	private String q;
	public void setQ(String q) {
		this.q = q;
	}

	//属性驱动，用来接收上传的文件
	private File regionFile;
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	@Autowired
	private RegionService regionService;
	/*
	 * 处理导入区域xls表的方法
	 */
	public String importXls() throws FileNotFoundException, IOException {
		ArrayList<Region> regionList = new ArrayList<Region>();
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
		int rowNum = row.getRowNum();
		if(rowNum==0)
		{
			continue;//表头数据跳过不设置。
		}
		//从sheet中获得数据设置到region对象中
		String id = row.getCell(0).getStringCellValue();
		String province = row.getCell(1).getStringCellValue();
		String city = row.getCell(2).getStringCellValue();
		String district = row.getCell(3).getStringCellValue();
		String postcode = row.getCell(4).getStringCellValue();
		//包装一个区域对象
		Region region = new Region(id, province, city, district, postcode, null, null, null);
		
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province + city + district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(headByString);
		//城市编码---->>shijiazhuang
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");
		region.setShortcode(shortcode);
		region.setCitycode(citycode);
		regionList.add(region);
		regionService.saveBatch(regionList);
		}
		return NONE;
	}
	/**
	 * 分页查询
	 * @throws Exception 
	 */
	public String pageQuery() throws Exception{
		regionService.pageQuery(pageBean);
		this.java2json(pageBean, 
					new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}
	public String listajax() {
		List<Region> list=null;
		if(StringUtils.isNotBlank(q))
		{
			list=regionService.findListByq(q);
		}else {
			list=regionService.findAll();
		}
		this.java2Json(list, new String[]{"subareas"});
		return NONE;
		
	}
	
}
