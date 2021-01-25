package com.example.search.services.impl;

import com.example.search.models.BusinessProfile;
import com.example.search.repositories.BusinessRepository;
import com.example.search.services.BusinessService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    private static final String BUSINESS_INDEX = "businessprofile";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    BusinessRepository businessRepository;

    @Override
    public BusinessProfile save(BusinessProfile profile) {
        return businessRepository.save(profile);
    }

    @Override
    public List<BusinessProfile> findAll() {
        Iterable<BusinessProfile> profileIterable = businessRepository.findAll();
        List<BusinessProfile> profileList = new ArrayList<>();
        profileIterable.forEach(profileList::add);
        return profileList;
    }

    @Override
    public BusinessProfile findById(String id) {
        return businessRepository.findById(id).get();
    }

    @Override
    public void deleteAll() {
        businessRepository.deleteAll();
    }

    @Override
    public List<BusinessProfile> processSearch(String query) {
        QueryBuilder queryBuilder=
                QueryBuilders
                        .multiMatchQuery(query,"name", "description")
                        .fuzziness(Fuzziness.AUTO);

        Query searchQuery=new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<BusinessProfile> profileHits=
                elasticsearchOperations
                        .search(searchQuery, BusinessProfile.class,
                                IndexCoordinates.of(BUSINESS_INDEX));

        List<BusinessProfile> profileMatches=new ArrayList<BusinessProfile>();
        profileHits.forEach(searchHit -> {
            profileMatches.add(searchHit.getContent());
        });
        return profileMatches;
    }
}
