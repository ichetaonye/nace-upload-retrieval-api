package com.test.naceapi.repository;

import com.test.naceapi.domain.transaction.NaceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class NaceDataTest {

    @Autowired
    NaceRepository naceRepository;

    @Test
    public void when_saved_nace_details_should_be_successful() {
        NaceEntity naceEntity = fetchAStubNaceEntity();
        NaceEntity anotherNaceEntity = new NaceEntity("2346", "level 2", "code 2", "some description", "parent", "includes", "also includes", "ruling", "excludes", "referenc");
        naceRepository.saveAll(List.of(naceEntity, anotherNaceEntity));

        Iterable<NaceEntity> savedEntities = naceRepository.findAll();
        assertThat(savedEntities).hasSize(2).contains(naceEntity, anotherNaceEntity);
    }

    @Test
    public void when_fetching_saved_entity_should_be_successful() {
        NaceEntity naceEntity  = fetchAStubNaceEntity();
        naceRepository.save(naceEntity);

        NaceEntity result = naceRepository.findByNaceOrder("2345");

        assertEquals(result, naceEntity);
    }

    @Test
    public void should_find_no_entity_if_repository_is_empty() {
        NaceEntity tutorials = naceRepository.findByNaceOrder("test");
        assertThat(tutorials).isNull();
    }

    @Test
    public void should_find_no_entity_if_record_does_not_exist() {
        NaceEntity naceEntity  = fetchAStubNaceEntity();
        naceRepository.save(naceEntity);

        NaceEntity tutorials = naceRepository.findByNaceOrder("test");
        assertThat(tutorials).isNull();
    }

    private NaceEntity fetchAStubNaceEntity() {
        return new NaceEntity("2345", "level 1", "code 1", "some description", "parent", "includes", "also includes", "ruling", "excludes", "reference");
    }
}
