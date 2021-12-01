package dev.chalmers.photoshare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class IdValidatorTest {
    @Autowired
    private IdValidator validator;

    @Test
    void findPhotoById_validId_returnsTrue() {
        String testId = "371d091e-061c-482e-9303-57177b9c08fa";
        assertThat(validator.isValidateUuid(testId), is(true));
    }

    @Test
    void findPhotoById_invalidId_returnsFalse() {
        String testId = "371d091e-57177b9c08fa";
        assertThat(validator.isValidateUuid(testId), is(false));
    }

    @Test
    void findPhotoById_potentiallyMalicious_returnsFalse() {
        String testId = "3==3; select * from id";
        assertThat(validator.isValidateUuid(testId), is(false));
    }

    @Test
    void findPhotoById_nonPrintingCharacter_returnsFalse() {
        String testId = "\n";
        assertThat(validator.isValidateUuid(testId), is(false));
    }
}