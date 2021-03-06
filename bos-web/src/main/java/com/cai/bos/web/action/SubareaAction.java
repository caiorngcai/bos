/**
 * 
 */
package com.cai.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Region;
import com.cai.bos.domain.Subarea;
import com.cai.bos.service.SubareaService;
import com.cai.bos.utils.FileUtils;

import freemarker.core.ReturnInstruction.Return;

/**
 * @author crc
 *	@date 2017年11月6日 上午9:24:18
 */
@Controller
public class SubareaAction extends BaseAction<Subarea>{
	//属性驱动接受定区id
	private String decidedzoneId;
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	@Autowired
	private SubareaService subareaService;
	public String add() {
		subareaService.save(model);
		return LIST;
	}
	public String pageQuery() {
		//获得离线查询对象，来动态添加模糊查询条件
		DetachedCriteria dc=pageBean.getDetachedCriteria();
		String addresskey=model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey))
		{
			//添加模糊查询条件
			dc.add(Restrictions.like("addresskey","%"+addresskey+"%"));
		}
		Region region=model.getRegion();
		if(region!=null)
		{
			String province=region.getProvince();
			String city=region.getCity();
			String district=region.getDistrict();
			//创建多表查询的别名，默认为内连接查询
			dc.createAlias("region","re");
			if(StringUtils.isNotBlank(province)){
				//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("re.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				//添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("re.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				//添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("re.district", "%"+district+"%"));
			}

		}
		subareaService.pageQuery(pageBean);
		this.java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});
		return NONE;
	}
	
	public String exportXls() throws IOException {
		//第一步：查询所有的分区数据
		List<Subarea> list=subareaService.findAll();
		//第二步：使用POI将数据写到Excel文件中
				//在内存中创建一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
			HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("分区编号");
			row.createCell(1).setCellValue("开始编号");
			row.createCell(2).setCellValue("结束编号");
			row.createCell(3).setCellValue("位置信息");
			row.createCell(4).setCellValue("省市区");
		//第三步：使用输出流进行文件下载（一个流、两个头）
			for (Subarea subarea :list) {
				HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
				dataRow.createCell(0).setCellValue(subarea.getId());
				dataRow.createCell(1).setCellValue(subarea.getStartnum());
				dataRow.createCell(2).setCellValue(subarea.getEndnum());
				dataRow.createCell(3).setCellValue(subarea.getPosition());
				dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
			}
			String fileName="分区数据.xls";
			String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
		//获取客户端浏览器类型
			ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setContentType(mimeType);
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			//对文件头进行中文编解码处理
			fileName=FileUtils.encodeDownloadFilename(fileName, agent);
			ServletActionContext.getResponse().setHeader("content-disposition",
					"attachment;filename="+fileName);
			workbook.write(outputStream);
			return NONE;
	}
	/*
	 * 查询未关联到定区的所有分区，非定区添加的分区下拉选择栏提供数据
	 */
	public String listajax() {
		List<Subarea> list=subareaService.findListNotAssociation();
		this.java2Json(list, new String[]{"decidedzone","region"});
		return NONE;
	}
	/*
	 * 根據定区id查询关联分区的方法
	 */
	public String findListByDecidedzoneId() {
		List<Subarea> list=subareaService.findListByDecidedzoneId(decidedzoneId);
		this.java2Json(list,new String[]{"decidedzone","subareas"});
		return NONE;
		
	}
}
