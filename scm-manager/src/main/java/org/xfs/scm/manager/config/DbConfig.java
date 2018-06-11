package org.xfs.scm.manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"org.xfs.scm.**.mapper"})
public class DbConfig {
}
