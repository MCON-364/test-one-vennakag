package edu.touro.las.mcon364.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicStreamQuizTest {
    public BasicStreamsQuiz test;

    @BeforeEach
    void setUp() {
        test = new BasicStreamsQuiz();
    }

    @Test
    void testGetSortedCourseNames(){
        List<String> names = test.getSortedCourseNames();
        assertNotNull(names);
        assertTrue( names.getFirst()=="Algorithms");
    }

    @Test
    void testCountScoresAtLeast(){
        assertEquals(6, test.countScoresAtLeast(90) );
    }

    @Test
    void testFirstLongWord(){
        List<String> words = List.of("math", "note", "field", "calculator");
        assertEquals("field", test.firstLongWord(words, 5).get());
    }

    @Test
    void testSquareAll(){

    }
}
