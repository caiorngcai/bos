/**
 * 
 */
package com.cai.bos.service.impl;

import java.sql.Timestamp;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.crm.ICustomerService;
import com.cai.bos.dao.DecidedzoneDao;
import com.cai.bos.dao.NoticebillDao;
import com.cai.bos.dao.WorkbillDao;
import com.cai.bos.domain.Decidedzone;
import com.cai.bos.domain.Noticebill;
import com.cai.bos.domain.Staff;
import com.cai.bos.domain.User;
import com.cai.bos.domain.Workbill;
import com.cai.bos.service.NoticebillService;
import com.cai.bos.utils.BOSUtil;

/**
 * @author crc
 *	@date 2017年11月17日 上午12:46:48
 */
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {

	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private NoticebillDao noticebillDao;
	@Autowired
	private WorkbillDao workbillDao;
	@Autowired
	private ICustomerService customerService;
	/* 
	 * 保存业务通知单的方法
	 */
	public void save(Noticebill model) {
		//从session中得到登陆的用户
		User user=BOSUtil.getLoginUser();
		model.setUser(user);
		noticebillDao.save(model);
		String pickaddress=model.getPickaddress();
		//调用webservice根据地址查询定区id的方法，为自动分单提供基础支持
		String decidedzoneId = customerService.finddecidedzoneIdByAddress(pickaddress);
		if(decidedzoneId!=null){
			//定区id不为null，说明可以进行自动分单操作
			Decidedzone decidedzone=decidedzoneDao.findById(decidedzoneId);
			//根据定区获得取派员数据
			Staff staff=decidedzone.getStaff();
			model.setStaff(staff);
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);//分单类型自动分单
			//产生一个新的工单
			Workbill workbill=new Workbill();
			workbill.setAttachbilltimes(0);//新单追单次数为零
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebill(model);
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态为未取件
			workbill.setRemark(model.getRemark());
			workbill.setStaff(staff);//取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workbillDao.save(workbill);
		}else {
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);//分单类型设置为手动分单
		}
	}

}
