package com.example.reservehaja.data.repo;

import com.example.reservehaja.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminId(String userId);

}
