package com.cai.bos.dao.base.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cai.bos.dao.base.*;
import com.cai.bos.domain.Region;
import com.cai.bos.utils.PageBean;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * dao层基础类，包含增删改查方法
 *
 * @author crc
 * @create 2017-10-26 12:48
 **/

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> entity;
    //因为使用注解开发，需要注入sessionFactory才能获得模板对象。
    @Resource
    public  void  setMySessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    //在构造类中在运行期获得T对象的实体类
    public BaseDaoImpl()
    {
       ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types= (Type[]) genericSuperclass.getActualTypeArguments();
        entity= (Class<T>) types[0];
    }
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }


    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }


    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }


    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entity,id);
    }


    public List<T> findAll() {
        String hql="FROM "+entity.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }
	/*
	 * 通用的更新方法
	 * @see com.cai.bos.dao.base.BaseDao#executeUpdate(java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public void executeUpdate(String queryName, Object... objects) {
		Session session=this.getSessionFactory().getCurrentSession();
		Query query=session.getNamedQuery(queryName);
		int i=0;
		for (Object object : objects) {
			query.setParameter(i++,object);
		}
		query.executeUpdate();
	}
	/* (non-Javadoc)
	 * 通用的分页查询方法，没有返回值，pagebean作为参数传进来即被修改
	 * 在此处只要获得pagebean的总记录数和分页数据集合属性即可
	 * @see com.cai.bos.dao.base.BaseDao#queryQuery(com.cai.bos.utils.PageBean)
	 */
	public void pageQuery(PageBean pageBean) {
		int currentPage=pageBean.getCurrentPage();
		int pageSize=pageBean.getPageSize();
		DetachedCriteria detachedCriteria=pageBean.getDetachedCriteria();
		//先查询总记录数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> countList=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count=countList.get(0);
		pageBean.setTotal(count.intValue());
		
		//获得分页数据集合
		detachedCriteria.setProjection(null);//把查询总数的限制条件去掉
		//设置hibernate封装查询结果，把多表查询结果封装成根对象返回结果，即分区对象返回，默认为把
		//结果的两个对象封装成数组返回。
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult=(currentPage-1)*pageSize;
		int maxResults=pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
		
	}
	/* 
	 * 保存或者修改
	 */
	public void saveOrUpdate(T entity) {
			this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
}
