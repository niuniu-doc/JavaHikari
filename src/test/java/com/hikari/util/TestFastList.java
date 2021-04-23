package com.hikari.util;

import com.hikari.mocks.StubStatement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.sql.Statement;
import java.util.ArrayList;

public class TestFastList {
    @Test
    public void testAddRemove() {
        ArrayList<Statement> verifyList = new ArrayList<>();
        FastList<Statement> list = new FastList<>(Statement.class);

        for (int i = 0; i < 32; i++) {
            StubStatement stubStatement = new StubStatement(null);
            list.add(stubStatement);
            verifyList.add(stubStatement);
        }

        for (int i = 0; i < 32; i++) {
            assertNotNull("Element " + i + "was null but should be " + verifyList.get(i), list.get(0));
            int size = list.size();
            list.remove(verifyList.get(i));
            assertSame(size-1, list.size());
        }
    }
}
