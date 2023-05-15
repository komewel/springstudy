package com.gdu.app12.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan(basePackages = {"com.gdu.app12.mapper"}) 			// @Mapper가 존재하는 클래스 패키지를 작성한다(이름도 BoardMapper)
@PropertySource(value = {"classpath:application.properties"}) // 프로퍼티 파일에 적힌 속성을 읽어오자!, 리소스 파일아래 따로 폴더에 안넣어놨으니 이렇게 간단하게 적힌다.
@EnableTransactionManagement // transaction 처리를 허용하겠다. 이게 있어야 한다.
@Configuration
public class DBConfig {
	
	@Autowired
	private Environment env; // 스프링에서 따로 관리하는 클래스이다 
	// Connection Pool에 관련된, application.properties에 써있는 설정값을 읽어서 bean객체화를 시키자, 분리시키는 이유는 보안을 위해서 각종 개인정보가 들어있을텐데(gitignore 할거임)
	// HikariConfig Bean
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name")); // oracle.jdbc.OracleDriver 값이 들어갔다, 
		hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));					// legacy에서는 이렇게 안해도 되지만 boot로 가면 이렇게 쓰기 때문에 나중에 수정 안하려고
		hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
		hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
		return hikariConfig;
	}
	
	
	// HikariDataSource Bean
	@Bean(destroyMethod = "close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig()); 
	}
	// hikari 공식홈페이지 등록된 사용법이다, 이렇게 되면 hikari가 커넥션풀을 관장한다 
	
	// sqlSessionFactory Bean
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception { // try catch문이 필요해서
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(hikariDataSource());
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
		return bean.getObject(); // factory가 반환된다, 팩토리가 만들어진다.
	}
	// sqlSessionTemplate Bean (기존의 SqlSession이 이런 네이밍으로 바뀌었다)
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception { // 위에 애가 예외를 던져서 여기도 해줘야함
		return new SqlSessionTemplate(sqlSessionFactory()); // 메소드를 전달한다
	}
	
	// 뭔가 줄줄이 bean을 만들면 1번째를 만들면 2번째가 재료로 쓰고 또 3번쨰는 2번째를 .... 결론적으로는 최종적으로 sqlsessiontemplate를 만들려고 이렇게 한거다.
	
	// TracsactionMannager Bean 
	@Bean
	public TransactionManager transActionManager() {
		return new DataSourceTransactionManager(hikariDataSource());
	}
}
