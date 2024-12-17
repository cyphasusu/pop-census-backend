package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.deletedAt is NULL ORDER BY u.id DESC") //taking this out still gives same effect
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.deletedAt is NULL ORDER BY u.id DESC")
    Page<User> findAll(Specification<User> spec, Pageable pageable);
    //List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.id = ?1 AND u.deletedAt is NULL")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.userResetToken = ?1 AND u.deletedAt is NULL")
    Optional<User> findByResetToken(String reset_token);
}