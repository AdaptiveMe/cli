/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.cli.command.builtin.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Florent Benoit
 */
public class AsciiArrayTest {

    @Test
    public void testEmptyArray() {
        AsciiArray asciiArray = new AsciiArray();
        String result = asciiArray.toAscii();
        assertTrue(result.length() == 0);
    }

    @Test
    public void testColumnsSize() {
        List<String> column1 = Arrays.asList("a", "ab", "abc", "a");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1);
        List<Integer> columnsSize = asciiArray.getColumnsSize();
        assertNotNull(columnsSize);
        assertEquals(1, columnsSize.size());
        assertEquals(Integer.valueOf(3), columnsSize.get(0));
    }

    @Test
    public void testColumnsSizeTwoColumns() {
        List<String> column1 = Arrays.asList("a", "ab", "abcdef", " abcdef ");
        List<String> column2 = Arrays.asList("defgh", "d", "e", "f");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1, column2);
        List<Integer> columnsSize = asciiArray.getColumnsSize();
        assertNotNull(columnsSize);
        assertEquals(2, columnsSize.size());
        assertEquals(Integer.valueOf(8), columnsSize.get(0));
        assertEquals(Integer.valueOf(5), columnsSize.get(1));
    }

    @Test
    public void testColumnsSizeWihTitle() {
        List<String> titles = Arrays.asList("Col1", " My Column 2");
        List<String> column1 = Arrays.asList("a", "ab", "abcdef", " abcdef ");
        List<String> column2 = Arrays.asList("defgh", "d", "e", "f");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1, column2).withTitle(titles);
        List<Integer> columnsSize = asciiArray.getColumnsSize();
        assertNotNull(columnsSize);
        assertEquals(2, columnsSize.size());
        assertEquals(Integer.valueOf(8), columnsSize.get(0));
        assertEquals(Integer.valueOf(12), columnsSize.get(1));
    }

    @Test
    public void testGetBorderLine() {
        List<String> column1 = Arrays.asList("a", "ab", "abc", "abcd");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1);

        String line = asciiArray.getBorderLine();

        // 4 spaces for the column
        assertEquals("+----+", line);

    }

    @Test
    public void testGetBorderLineWithTitle() {
        List<String> column1 = Arrays.asList("a", "ab", "abc", "abcd");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1).withTitle("Identifier");

        String line = asciiArray.getBorderLine();

        // 10 - for the title
        assertEquals("+----------+", line);

    }

    @Test
    public void testOneColumn() {
        List<String> column1 = Arrays.asList("row1", "row2", "row3");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1);
        String result = asciiArray.toAscii();
        assertEquals("+----+\n|row1|\n|row2|\n|row3|\n+----+", result);
    }

    @Test
    public void testTwoColumns() {
        List<String> column1 = Arrays.asList("row1", "row2", "row3");
        List<String> column2 = Arrays.asList("1", "2", "3");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1, column2);
        String result = asciiArray.toAscii();
        assertEquals("+----+-+\n|row1|1|\n|row2|2|\n|row3|3|\n+----+-+", result);
    }

    @Test
    public void testTwoColumnsWithTitle() {
        List<String> column1 = Arrays.asList("row1", "row2", "row3");
        List<String> column2 = Arrays.asList("1", "2", "3");
        List<String> titles = Arrays.asList("name", "id");
        AsciiArray asciiArray = new AsciiArray().withColumns(column1, column2).withTitle(titles);
        String result = asciiArray.toAscii();
        assertEquals("+----+--+\n|name|id|\n+----+--+\n|row1| 1|\n|row2| 2|\n|row3| 3|\n+----+--+", result);
    }

}
