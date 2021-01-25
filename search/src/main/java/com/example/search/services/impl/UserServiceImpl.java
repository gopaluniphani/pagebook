package com.example.search.services.impl;

import com.example.search.models.UserProfile;
import com.example.search.repositories.UserRepository;
import com.example.search.services.UserService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String USER_INDEX = "userprofile";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile save(UserProfile profile) {
        return userRepository.save(profile);
    }

    @Override
    public List<UserProfile> findAll() {
        Iterable<UserProfile> profileIterable = userRepository.findAll();
        List<UserProfile> profileList = new ArrayList<>();
        profileIterable.forEach(profileList::add);
        return profileList;
    }

    @Override
    public UserProfile findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<UserProfile> processSearch(String query) {
        QueryBuilder queryBuilder=
                QueryBuilders
                        .multiMatchQuery(query,"firstName", "lastName")
                        .fuzziness(Fuzziness.AUTO);

        Query searchQuery=new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<UserProfile> profileHits=
                elasticsearchOperations
                        .search(searchQuery, UserProfile.class,
                                IndexCoordinates.of(USER_INDEX));

        List<UserProfile> profileMatches=new ArrayList<UserProfile>();
        profileHits.forEach(searchHit -> {
            profileMatches.add(searchHit.getContent());
        });
        return profileMatches;
    }
}
