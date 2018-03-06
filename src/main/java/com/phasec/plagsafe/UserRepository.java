package com.phasec.plagsafe;

import com.phasec.plagsafe.objects.UserObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Integer> {
    //Query to check if a user with this username and password is present in the database
    @Query("SELECT count(user_name) FROM registered_user WHERE user_name=?1 AND secret=?2")
    public int find(String user_name, String secret);
}
