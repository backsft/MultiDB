package com.MUltipleDatabaseImplement;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.MUltipleDatabaseImplement.MYSQL.Entities.Student;
import com.MUltipleDatabaseImplement.MYSQL.MysqlRepo.MysqlRepo;

@SpringBootTest
class MUltipleDatabaseImplementApplicationTests {

	@Autowired
	MysqlRepo mysqlRepo;
	
	
	@Test
	public void mysqltest() {
		
		
		Optional<Student> findById = mysqlRepo.findById(1);
		System.out.println(" test "+findById.get());
		
	}
}
