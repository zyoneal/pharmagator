package com.eleks.academy.pharmagator.dataproviders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PharmacyLiki24DataProviderTest {

    private PharmacyLiki24DataProvider subject;

    private final String BASE_URL = "https://liki24.com/vnext/api/catalogue/8000001/products";

    @Test
    void loadData() {
    }

}
