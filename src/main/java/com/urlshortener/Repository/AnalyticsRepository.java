package com.urlshortener.Repository;

import com.urlshortener.Model.AnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AnalyticsEntity,Long> {

}
