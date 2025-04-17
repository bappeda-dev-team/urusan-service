package kk.kertaskerja.urusanservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UrusanValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectValidationSuccess() {
        var urusan = Urusan.of("1", "Test Urusan");
        Set<ConstraintViolation<Urusan>> violations = validator.validate(urusan);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenKodeUrusanNotDefinedThenValidationFailure() {
        var urusan = Urusan.of("", "Test Urusan");

        Set<ConstraintViolation<Urusan>> violations = validator.validate(urusan);
        assertThat(violations).hasSize(2);

        List<String> errorMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        assertThat(errorMessages)
                .contains("Format kode tidak valid")
                .contains("Kode urusan wajib terisi");

    }

    @Test
    void whenNamaUrusanNotDefinedThenValidationFailure() {
        var urusan = Urusan.of("X", "");

        Set<ConstraintViolation<Urusan>> violations = validator.validate(urusan);
        assertThat(violations).hasSize(1);

        String errorMessages = violations.stream().findFirst().get().getMessage();
        assertThat(errorMessages)
                .isEqualTo("Nama urusan wajib terisi");
    }
}
