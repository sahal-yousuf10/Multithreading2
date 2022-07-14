package com.example.sahal.Springbootmultithreading2.Repository;

import com.example.sahal.Springbootmultithreading2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long>{
}
