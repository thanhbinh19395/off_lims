package com.gst.repository;

import com.gst.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    User findByUsername(String username);
}
