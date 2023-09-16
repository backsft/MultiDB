package com.MUltipleDatabaseImplement.MYSQL.MysqlRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MUltipleDatabaseImplement.MYSQL.Entities.Student;

public interface MysqlRepo extends JpaRepository<Student, Integer> {

}
