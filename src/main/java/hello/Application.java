package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		Item i = new Item();
		log.info("starting");
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Item("a"));
			repository.save(new Item("b"));
		};
	}
}
