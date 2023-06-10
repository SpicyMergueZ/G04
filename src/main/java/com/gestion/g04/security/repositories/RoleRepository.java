package com.gestion.g04.security.repositories;

import com.gestion.g04.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, String>{



}
