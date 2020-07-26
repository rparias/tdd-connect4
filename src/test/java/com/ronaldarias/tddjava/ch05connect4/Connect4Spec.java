package com.ronaldarias.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class Connect4Spec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private OutputStream output;
    private Connect4 tested;

    @Before
    public void init() {
        output = new ByteArrayOutputStream();
        tested = new Connect4(new PrintStream(output));
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

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        assertThat(tested.putDiscInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void
    whenNoMoreRoomInColumnThenRuntimeException() {
        int column = 1;
        int maxDiscsInColumn = 6; // the number of rows
        for (int times = 0; times < maxDiscsInColumn; ++times) {
            tested.putDiscInColumn(column);
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("No more room in column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer(), is("R"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        tested.putDiscInColumn(1);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }

    @Test
    public void whenAskedForCurrentPlayerThenOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString("Player R turn"));
    }

    @Test
    public void whenADiscIsIntroducedThenBoardIsPrinted() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(output.toString(), containsString("| |R| | | | | |"));
    }

    @Test
    public void whenTheGameStartsThenItIsNotFinished() {
        assertFalse("The game must not be finished", tested.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedThenGameIsFinished() {
        for (int row = 0; row < 6; row++)
            for (int column = 0; column < 7; column++)
                tested.putDiscInColumn(column);
        assertTrue("The game must be finished",tested.isFinished());
    }

    @Test
    public void when4VerticalDiscsAreConnectedThenPlayerWins() {
        for (int row = 0; row < 3; row++) {
            tested.putDiscInColumn(1); // R
            tested.putDiscInColumn(2); // G
        }
        assertThat(tested.getWinner(), isEmptyString());
        tested.putDiscInColumn(1); // R
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4HorizontalDiscsAreConnectedThenPlayerWins() {
        int column;
        for (column = 0; column < 3; column++) {
            tested.putDiscInColumn(column); // R
            tested.putDiscInColumn(column); // G
        }
        assertThat(tested.getWinner(),
                isEmptyString());
        tested.putDiscInColumn(column); // R
        assertThat(tested.getWinner(), is("R"));
    }
}
