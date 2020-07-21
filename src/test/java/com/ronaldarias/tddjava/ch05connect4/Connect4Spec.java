package com.ronaldarias.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Connect4Spec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Connect4 tested;

    @Before
    public void init() {
        tested = new Connect4();
    }

    @Test
    public void whenTheGameIsStartedThenBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);
        tested.putDiscInColumn(column);
    }
}
