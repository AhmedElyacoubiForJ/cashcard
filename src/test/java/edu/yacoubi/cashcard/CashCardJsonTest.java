package edu.yacoubi.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

// Write a failing test
// Use TDD to test a JSON data contract
// Use TDD to test JSON deserialization

// Testing the Data Contract
@JsonTest
public class CashCardJsonTest {

    // JacksonTester is a convenience wrapper to the Jackson JSON parsing library.
    // It handles serialization and deserialization of JSON objects.
    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    void cashCardSerializationTest() throws IOException {
        // Given
        CashCard cashCard = new CashCard(99L, 123.45);

        // When Then
        assertThat(json.write(cashCard))
                .isStrictlyEqualToJson("expected.json");

        assertThat(json.write(cashCard))
                .hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashCard))
                .extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashCard))
                .hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashCard))
                .extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }
}
