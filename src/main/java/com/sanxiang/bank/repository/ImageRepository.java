package com.sanxiang.bank.repository;


import com.sanxiang.bank.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image,Long> {
}
