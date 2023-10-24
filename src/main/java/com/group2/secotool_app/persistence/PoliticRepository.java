package com.group2.secotool_app.persistence;

import com.group2.secotool_app.model.entity.Politic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticRepository extends JpaRepository<Politic,Long> {
    boolean existsByTitle(String title);
}
