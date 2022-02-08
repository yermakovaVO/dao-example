package liga.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@EntityScan(basePackages = {"entity", "model"})
public class PersistenceConfig {

	private static final Database primaryDatabase = Database.POSTGRESQL;

	//DataSource - управляет подключениями
	@Bean
	public DataSource getDataSource(DataSourceProperties dataSourceProperties) {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

		dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());
		dataSourceBuilder.url(dataSourceProperties.getUrl());
		dataSourceBuilder.username(dataSourceProperties.getUsername());
		dataSourceBuilder.password(dataSourceProperties.getPassword());

		return dataSourceBuilder.build();
	}

	@Bean(name = "primaryDatabase")
	public Database primaryDatabase() {
		return primaryDatabase;
	}

	//JdbcTemplate – центральный класс для выполнения запросов
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
