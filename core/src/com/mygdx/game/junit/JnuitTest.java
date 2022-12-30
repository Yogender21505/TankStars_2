package com.mygdx.game.junit;

import com.mygdx.game.explosion.Explosion;


//@ https://www.javatpoint.com/junit-tutorial
public class JnuitTest {


    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }

    public void setUp() throws Exception {
        System.out.println("before");
    }


    public void healthfinish(){
        System.out.println("To see explosion happened");
        assertNotEquals(0, Explosion.explosionhappen(5,30,621));
        assertEquals(1,Explosion.explosionhappen(5,30,621));
    }

    private void assertEquals(int i, int explosionhappen) {

    }

    private void assertNotEquals(int i, int explosionhappen) {
    }


    public void tearDown() throws Exception {
        System.out.println("after");
    }


    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }

}