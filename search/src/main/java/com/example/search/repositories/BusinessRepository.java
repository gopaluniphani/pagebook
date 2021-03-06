package com.example.search.repositories;

import com.example.search.models.BusinessProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends ElasticsearchRepository<BusinessProfile, String> {
}
