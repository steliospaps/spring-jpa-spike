package hello;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
//@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		Item i = new Item();
		log.info("starting");
		SpringApplication.run(Application.class);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean TwoIdenticalItemPersister twoIdenticalItemPersister(){
		return new TwoIdenticalItemPersister();
	}
	
	@Bean
	public CommandLineRunner demo(TransactionalVerifier ver) {
		return (args) -> {
			ver.verifyTransationality();
		};
	}
}
