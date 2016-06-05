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
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
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
	public CommandLineRunner demo(ItemRepository repository, TwoIdenticalItemPersister per) {
		return (args) -> {
			repository.deleteAll();
			try {
				per.inject2Identical();
				log.error("we should not be here");
			} catch (DataIntegrityViolationException e) {
				log.info("ignoring exception {}", e.getMessage());
			}
			long count = repository.count();
			if (count != 0) {
				throw new RuntimeException("partial data insertion detected");
			}
		};
	}

	@Transactional()
	public void inject2Identical(ItemRepository repository) {
		repository.save(new Item("a"));
		repository.save(new Item("a"));
	}
}
