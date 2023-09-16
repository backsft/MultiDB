package com.MUltipleDatabaseImplement.Postgres.familyRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MUltipleDatabaseImplement.Postgres.familyEntity.FamiltyInfo;

public interface FamilyRepo extends JpaRepository<FamiltyInfo, Integer> {

}
