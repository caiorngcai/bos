package com.cai.bos.dao.base.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cai.bos.dao.base.*;

import javax.annotation.Resource;
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
	
}
