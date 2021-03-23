package com.tasks.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PaymentRepository extends JpaRepository<Payments, String>{

	@Modifying
	@Query("update Payments p set p.strdata=:strdata where p.id =:id")
	void updateStrdata(@Param("id") String id,@Param("strdata") String strdata);
}
