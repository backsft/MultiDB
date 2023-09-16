package com.MUltipleDatabaseImplement.TestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MUltipleDatabaseImplement.MYSQL.Entities.Student;
import com.MUltipleDatabaseImplement.MYSQL.MysqlRepo.MysqlRepo;
import com.MUltipleDatabaseImplement.Postgres.familyEntity.FamiltyInfo;
import com.MUltipleDatabaseImplement.Postgres.familyRepo.FamilyRepo;

@RestController
public class MyTestController {
	
	
	@Autowired
	private MysqlRepo mysqlRepo;
	@Autowired
	private FamilyRepo familyRepo;
	
	
	@GetMapping("/insert")
	public String insert() {
		
		
		Student student=new Student();
		student.setName("kabir");
		
		FamiltyInfo familtyInfo=new FamiltyInfo();
		familtyInfo.setName("hazra ");
		
		mysqlRepo.save(student);
		
		familyRepo.save(familtyInfo);
		
		return "Success";
		
	}
	
	
	
	

}
