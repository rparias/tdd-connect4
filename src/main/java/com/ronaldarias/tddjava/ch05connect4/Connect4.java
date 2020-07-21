package com.ronaldarias.tddjava.ch05connect4;

public class Connect4 {
    private static final int COLUMNS = 7;

    public int getNumberOfDiscs() {
        return 0;
    }

    public void putDiscInColumn(int column) {
        checkColumn(column);
    }

    private void checkColumn(int column) {
        if (column < 0 || column >= COLUMNS) {
            throw new RuntimeException("Invalid column " + column);
        }
    }
}
