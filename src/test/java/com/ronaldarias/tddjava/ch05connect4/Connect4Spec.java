package com.ronaldarias.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Connect4Spec {

    Connect4 tested;

    @Before
    public void init() {
        tested = new Connect4();
    }

    @Test
    public void whenTheGameIsStartedThenBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }
}
