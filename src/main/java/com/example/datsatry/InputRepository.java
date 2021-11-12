package com.example.datsatry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InputRepository extends CrudRepository<Input, Long> {

    //List<Input> findByLastName(String lastName);

    //Input findById(long id);
}

