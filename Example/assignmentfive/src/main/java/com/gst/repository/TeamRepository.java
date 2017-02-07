package com.gst.repository;

import com.gst.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by Thanh Binh on 1/27/2017.
 */
public interface TeamRepository extends CrudRepository<Team,Integer> {
    Collection<Team> findByNameContaining(String name);
}
