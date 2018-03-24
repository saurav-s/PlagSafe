package com.phasec.plagsafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phasec.plagsafe.objects.UserObject;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Integer> {

}
