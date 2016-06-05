package hello;

import javax.persistence.EntityManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		Item i = new Item();
		log.info("starting");
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (args) -> {
			repository.deleteAll();
			// save a couple of customers
			try{
				inject2Identical(repository);
			}catch(DataIntegrityViolationException e) {
				log.info("ignoring exception {}", e.getMessage());
			}
			long count = repository.count();
			if(count != 0 ){
				throw new RuntimeException("partial data insertion detected");
			}
		};
	}
	@Transactional
	public void inject2Identical(ItemRepository repository) {
		repository.save(new Item("a"));
		repository.save(new Item("a"));
	}
}
