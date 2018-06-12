package org.xfs.scm.platform.config.database.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"org.xfs.scm.**.mapper"})
public class DbConfig {
}
