package ee.maksimov.demo.spring.jpa.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ee.maksimov.demo.spring.jpa.SpringJpaApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaApplication.class, webEnvironment = RANDOM_PORT)
@ContextConfiguration(
  classes = AbstractIT.LiquibaseDataSourceConfig.class,
  initializers = AbstractIT.Initializer.class
)
public abstract class AbstractIT {

  private static final String POSTGRE_USERNAME = "postgres";
  private static final String POSTGRE_PASSWORD = "mysecretpassword";
  private static final String POSTGRE_DATABASE_NAME = "springjpa";

  private static PostgreSQLContainer postgre = new PostgreSQLContainer()
    .withDatabaseName(POSTGRE_DATABASE_NAME)
    .withUsername(POSTGRE_USERNAME)
    .withPassword(POSTGRE_PASSWORD);

  static {
    postgre.start();
  }

  public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
      TestPropertyValues.of("spring.datasource.url" + postgre.getJdbcUrl())
        .applyTo(context.getEnvironment());
    }

  }

  @Configuration
  public static class LiquibaseDataSourceConfig {

    @Bean
    @Profile("test")
    @LiquibaseDataSource
    public DataSource provideLiquibaseDataSource() {
      // Liquibase should use its own data source

      // path to changelog.xml is provided in 'application-test.properties'
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(postgre.getJdbcUrl());
      config.setUsername(POSTGRE_USERNAME);
      config.setPassword(POSTGRE_PASSWORD);
      config.setAutoCommit(true);
      config.setDriverClassName("org.postgresql.Driver");
      return new HikariDataSource(config);
    }

  }

}