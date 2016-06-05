package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TwoIdenticalItemPersister {
	@Autowired
	private ItemRepository repository;
	
	@Transactional()
	public void inject2Identical() {
		repository.save(new Item("a"));
		repository.save(new Item("a"));
	}

}
