package com.tasks.demo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;


import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class UniqueIdGenerator implements IdentifierGenerator, Configurable {

	
	public static final String UniqueId_GENERATOR_PARAM_KEY = "procedureParam";
	 
    private String procedureParam;
    
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		
		// @GenericGenerator에서 넘겨준 파라미터 꺼내기
		this.procedureParam = ConfigurationHelper.getString(UniqueId_GENERATOR_PARAM_KEY, params); 
		
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.format(cal.getTime())+UUID.randomUUID().toString().replace("-","").substring(0,12);
		
	}

}
