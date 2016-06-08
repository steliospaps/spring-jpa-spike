package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionalVerifier {
	@Autowired private ItemRepository repository; 
	@Autowired private TwoIdenticalItemPersister per;
	public void verifyTransationality() {
		clearTable(repository);
		if(true) return;
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
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void clearTable(ItemRepository repository) {
		log.info("about to delete all");
		repository.deleteAllEntities();
		log.info("deleted all");
		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void inject2Identical(ItemRepository repository) {
		repository.save(new Item("a"));
		repository.save(new Item("a"));
	}

}
