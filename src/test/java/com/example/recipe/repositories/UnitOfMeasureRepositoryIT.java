package com.example.recipe.repositories;

import com.example.recipe.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository repository;

    @Test
    public void findByDescription() {
        String description = "Tablespoon";

        Optional<UnitOfMeasure> unitOfMeasure = repository.findByDescription(description);

        Assert.assertEquals(description, unitOfMeasure.get().getDescription());
    }
}