package com.jimg.myalbatross.shared.application;

import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestCase {
    protected static void assertMyalbatrossException(MyalbatrossError error, MyalbatrossException exception) {
        assertEquals(error.getMessage(), exception.getMessage());
        assertEquals(error.getStatus(), exception.getStatus());
    }

    protected static <T, U> void assertListEquals(List<T> expected, List<U> actual, BiConsumer<T, U> assertMethod) {
        Assertions.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertMethod.accept(expected.get(i), actual.get(i));
        }
    }
}

