package org.xfs.scm.platform.config.spring;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.xfs.scm.**.web"})
public class MvcConfig extends WebMvcConfigurerAdapter {

	private final static Logger logger= LoggerFactory.getLogger(MvcConfig.class);
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(30);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("ronnie-task-");
		executor.initialize();
		configurer.setDefaultTimeout(30000);
		configurer.setTaskExecutor(executor);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(60*60*24*30);

		//registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}


	/*
	 * Configure ContentNegotiatingViewResolver 视图配置
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jspViewResolver());
		// resolvers.add(freeMarkerViewResolver());
		resolver.setViewResolvers(resolvers);
		MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
		// JsonEncoding encoding=new ();
		defaultView.setEncoding(JsonEncoding.UTF8);

		defaultView.setExtractValueFromSingleKeyModel(true);
		defaultView.setPrettyPrint(true);
		resolver.setDefaultViews(Lists.<View>newArrayList(defaultView));
		return resolver;
	}

	/**
	 * 描述 : <文件上传处理器>. <br>
	 *<p>
	 <使用方法说明>
	 </p>
	 * @return
	 */
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		if(logger.isDebugEnabled()){
			logger.info("CommonsMultipartResolver");
		}
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
	private InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * restful json 格式化覆写
	 * @param converters
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

		Optional<HttpMessageConverter<?>> converterFound;
		converterFound = converters.stream().filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();

		if (converterFound.isPresent()) {
			final AbstractJackson2HttpMessageConverter converter;
			converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
			converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			// converter.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//日期格式
		}
	}
}
