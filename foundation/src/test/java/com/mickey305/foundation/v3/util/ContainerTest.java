package com.mickey305.foundation.v3.util;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContainerTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCase_01_01() throws Exception {
        final int cmdCnt = 100;
        List<Executable<Integer>> commands = new ArrayList<>();
        for (int i = 0; i < cmdCnt; i++)
            commands.add(new TestCommand(i));
//        for (Executable<Integer> command: commands)
//            Log.i("code="+command.hashCode());
        Container<Integer> container = new Container<>(commands);
        Thread th = new Thread(container);
        Log.i("main: tread start");
        th.start();
        Thread.sleep(10 * 1000);
        container.shutdown();
        Log.i("main: tread end");
        Log.i("main: result...");
        for (Map.Entry<Integer, Integer> entry: container.getResultPool().entrySet()) {
            Log.i("key="+entry.getKey()+", value="+entry.getValue());
        }
        Assert.assertEquals(cmdCnt, container.getCommands().size() + container.getExecutedCommands().size());
        Assert.assertEquals(new Integer(0), container.getResultPool().get(629));
        Assert.assertEquals(new Integer(0), container.getResultPool().get(630));
        Assert.assertEquals(new Integer(0), container.getResultPool().get(631));
    }

    private class TestCommand implements Executable<Integer> {
        private int id;

        public TestCommand(int id) {
            this.setId(id);
        }

        @Override
        public Integer execute() {
            try {
                Thread.sleep(3000);
                Log.i("command invoked! id=[" + (this.getId()+1) + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }
}