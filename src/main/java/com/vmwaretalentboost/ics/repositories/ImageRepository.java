package com.vmwaretalentboost.ics.repositories;

import com.vmwaretalentboost.ics.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByUrl(String url);

    boolean existsByUrl(String url);

    void deleteByUrl(String url);
}
