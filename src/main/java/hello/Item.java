package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity(name="ITEM")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Item {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String uniq;
	Item(String uniq){
		this.uniq = uniq;
	}
}
