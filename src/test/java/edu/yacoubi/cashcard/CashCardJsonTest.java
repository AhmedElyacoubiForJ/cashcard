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
// Serialization / Deserialization
@JsonTest
public class CashCardJsonTest {

    // JacksonTester is a convenience wrapper to the Jackson JSON parsing library.
    // It handles serialization and deserialization of JSON objects.
    @Autowired
    private JacksonTester<CashCard> json;

    // 1. Testing the Data Contract
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

    // 2: Testing Deserialization
    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id":99,
                    "amount":123.45
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45));

        assertThat(json.parseObject(expected).id())
                .isEqualTo(99);
        assertThat(json.parseObject(expected).amount())
                .isEqualTo(123.45);
    }
}
