package com.lmg.hibernate_1;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Data;

public class hibernateTest {

	@Test
	public void test() {
		//1. 创建sessionFactory对象
		SessionFactory sessionFactory = null;
		//1). 创建 Configuration 对象: 对应 hibernate 的基本配置信息和对象关系映射信息
		Configuration configuration = new Configuration().configure();

		//4.0  之前创建方式
		//sessionFactory = configuration.buildSessionFactory();
		
		//2). 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		//hibernate 的任何配置和服务都需要在该对象中注册后才能生效
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
											.buildServiceRegistry();
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//2.创建session
		Session session = sessionFactory.openSession();
		//3.开启事务
		Transaction transaction = session.beginTransaction();
		//4.执行sql(持久化操作必须在 session 事务管理下才会生效)
		session.save(new New("Java","Tom",new Date(new java.util.Date().getTime())));
		//5.提交事务
		transaction.commit();
		//6.关闭session
		session.close();
		//7.关闭sessionFactory
		sessionFactory.close();
	}

}
