package hello;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface ItemRepository extends CrudRepository<Item, Long>{
	@Transactional
	@Modifying
	@Query(value="delete from ITEM")
	public void deleteAllEntities();
}
