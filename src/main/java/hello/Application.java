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
//@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		log.info("starting");
		SpringApplication.run(Application.class);
	}
/*
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
*/
	@Bean
	public CommandLineRunner demo(ItemRepository rep) {
		return (args) -> {
			log.info("count of items {}",rep.count());
			log.info("deleteAll");
			rep.deleteAll();
			log.info("count of items {}",rep.count());
			Item item = new Item("hello");
			log.info("saving item {}",item);
			item = rep.save(item);
			log.info("saved item {}",item);
			log.info("count of items {}",rep.count());
			log.info("deleting item {}",item);
			rep.delete(item);
			log.info("count of items {}",rep.count());			
		};
	}
}
