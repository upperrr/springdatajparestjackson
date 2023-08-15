package th.com.proj.springdatajparestjackson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.com.proj.springdatajparestjackson.entity.Singers;

public interface SingersRepository extends JpaRepository<Singers, Integer> { //JpaRepository throught

}
