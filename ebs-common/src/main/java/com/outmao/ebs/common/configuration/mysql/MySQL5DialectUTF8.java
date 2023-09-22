package com.outmao.ebs.common.configuration.mysql;

import org.hibernate.dialect.MySQL5InnoDBDialect;

@SuppressWarnings("deprecation")
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {

	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}

}
