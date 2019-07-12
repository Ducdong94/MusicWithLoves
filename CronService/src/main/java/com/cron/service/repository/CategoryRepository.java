package com.cron.service.repository;

import com.cron.service.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);

    boolean existsByName(String name);
}
