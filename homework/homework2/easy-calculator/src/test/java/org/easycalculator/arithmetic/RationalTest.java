package org.easycalculator.arithmetic; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;

/** 
* Rational Test.
* 
* @author <Authors name> 
* @since <pre>ËÄÔÂ 6, 2019</pre> 
* @version 1.0 
*/ 
public class RationalTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: Rational(long num, long den)
     *
     */
    @Test
    public void testRationalA() throws Exception {
        Field num = null, den = null, sign = null;
        try {
            num = Rational.class.getDeclaredField("num");
            den = Rational.class.getDeclaredField("den");
            sign = Rational.class.getDeclaredField("sign");
            num.setAccessible(true);
            den.setAccessible(true);
            sign.setAccessible(true);
            Rational r = null;
            long[] a = {12, -12, 5, -5, 0, 0, 24},
                    b = {15, 15, -10, -10, 3, -4, 2},
                    n = {4, 4, 1, 1, 0, 0, 12},
                    d = {5, 5, 2, 2, 1, 1, 1};
            int[] s = {1, -1, -1, 1, 1, 1, 1};
            for(int i = 0; i < a.length; i++){
                r = new Rational(a[i], b[i]);
                Assert.assertEquals(n[i], num.get(r));
                Assert.assertEquals(d[i], den.get(r));
                Assert.assertEquals(s[i], sign.get(r));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                num.setAccessible(false);
                den.setAccessible(false);
                sign.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            new Rational(3, 0);
            Assert.fail("Exception expected");
        } catch (IllegalArgumentException e1) {}
        catch (Exception e) {
            Assert.fail("IllegalArgumentException expected");
        }
    }

    /**
     *
     * Method: Rational(long num)
     *
     */
    @Test
    public void testRationalB() throws Exception {
        Field num = null, den = null, sign = null;
        try {
            num = Rational.class.getDeclaredField("num");
            sign = Rational.class.getDeclaredField("sign");
            den = Rational.class.getDeclaredField("den");
            num.setAccessible(true);
            sign.setAccessible(true);
            den.setAccessible(true);
            Rational r = null;
            long[] a = {12, -12, 5, -5, 0, 24},
                    n = {12, 12, 5, 5, 0, 24};
            int[] s = {1, -1, 1, -1, 1, 1};
            for(int i = 0; i < a.length; i++){
                r = new Rational(a[i]);
                Assert.assertEquals(n[i], num.get(r));
                Assert.assertEquals(s[i], sign.get(r));
                Assert.assertEquals(1L, den.get(r));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                num.setAccessible(false);
                sign.setAccessible(false);
                den.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Method: Rational(Rational r)
     *
     */
    @Test
    public void testRationalC() throws Exception {
        Field num = null, den = null, sign = null;
        try {
            num = Rational.class.getDeclaredField("num");
            den = Rational.class.getDeclaredField("den");
            sign = Rational.class.getDeclaredField("sign");
            num.setAccessible(true);
            den.setAccessible(true);
            sign.setAccessible(true);
            Rational r1 = null, r2 = null;
            long[] a = {12, -12, 5, -5, 0, 24},
                    b = {15, 15, -10, -10, 3, 2},
                    n = {4, 4, 1, 1, 0, 12},
                    d = {5, 5, 2, 2, 1, 1};
            int[] s = {1, -1, -1, 1, 1, 1};
            for(int i = 0; i < a.length; i++){
                r1 = new Rational(a[i], b[i]);
                r2 = new Rational(r1);
                Assert.assertEquals(n[i], num.get(r2));
                Assert.assertEquals(d[i], den.get(r2));
                Assert.assertEquals(s[i], sign.get(r2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                num.setAccessible(false);
                den.setAccessible(false);
                sign.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Method: Rational(String str)
     *
     */
    @Test
    public void testRationalD() throws Exception {
        Field num = null, den = null, sign = null;
        try {
            num = Rational.class.getDeclaredField("num");
            den = Rational.class.getDeclaredField("den");
            sign = Rational.class.getDeclaredField("sign");
            num.setAccessible(true);
            den.setAccessible(true);
            sign.setAccessible(true);
            Rational r = null;
            String[] st = {"0.8", "0.800", "1.5", ".060", "23.060", "00000", "12", ""};
            long[] n = {4, 4, 3, 3, 1153, 0, 12, 0},
                    d = {5, 5, 2, 50, 50, 1, 1, 1};
            int[] s = {1, 1, 1, 1, 1, 1, 1, 1};
            for(int i = 0; i < st.length; i++){
                r = new Rational(st[i]);
                Assert.assertEquals(n[i], num.get(r));
                Assert.assertEquals(d[i], den.get(r));
                Assert.assertEquals(s[i], sign.get(r));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                num.setAccessible(false);
                den.setAccessible(false);
                sign.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] st2 = {"$26", "0.0.1", "-3"};
        for(int i = 0; i < st2.length; i++){
            try {
                new Rational(st2[i]);
                Assert.fail("Exception expected");
            } catch (ParseException e1) {
                System.out.println("ParseException caught: " + e1.getMessage());
            }
            catch (Exception e) {
                Assert.fail("ParseException expected");
            }
        }
    }

    /**
     *
     * Method: add(Rational r)
     *
     */
    @Test
    public void testAdd() throws Exception {
        Rational r1 = null, r2 = null, r = null, s = null;
        long[] a1 = {4, -2, 5, -5, 0, 24, 12},
                b1 = {5, 5, 10, -3, 3, 11, -3},
                a2 = {1, 15, 10, -10, 3, 9, 28},
                b2 = {5, 15, -10, -9, 3, -7, 7},
                n = {1, 3, -1, 25, 1, 69, 0},
                d = {1, 5, 2, 9, 1, 77, 1};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i], b1[i]);
            r2 = new Rational(a2[i], b2[i]);
            r = r1.add(r2);
            s = new Rational(n[i], d[i]);
            Assert.assertEquals(s, r);
        }
    }

    /**
     *
     * Method: subtract(Rational r)
     *
     */
    @Test
    public void testSubtract() throws Exception {
        Rational r1 = null, r2 = null, r = null, s = null;
        long[] a1 = {6, -2, 5, -5, 0, 24, 12},
                b1 = {5, 5, 10, -3, 3, 11, -15},
                a2 = {1, 15, 10, -10, 3, 9, -28},
                b2 = {5, 15, -10, -9, 3, -7, 35},
                n = {1, -7, 3, 5, -1, 267, 0},
                d = {1, 5, 2, 9, 1, 77, 1};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i], b1[i]);
            r2 = new Rational(a2[i], b2[i]);
            r = r1.subtract(r2);
            s = new Rational(n[i], d[i]);
            Assert.assertEquals(s, r);
        }
    }

    /**
     *
     * Method: multiply(Rational r)
     *
     */
    @Test
    public void testMultiply() throws Exception {
        Rational r1 = null, r2 = null, r = null, s = null;
        long[] a1 = {4, -2, 5, -5, 0, 0, 12, 16},
                b1 = {5, 5, 10, 3, 3, 2, -5, 75},
                a2 = {1, 15, 10, -10, 125, 0, 15, 30},
                b2 = {5, 15, -10, 9, 3, 3, 4, 16},
                n = {4, -2, -1, 50, 0, 0, -9, 2},
                d = {25, 5, 2, 27, 1, 1, 1, 5};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i], b1[i]);
            r2 = new Rational(a2[i], b2[i]);
            r = r1.multiply(r2);
            s = new Rational(n[i], d[i]);
            Assert.assertEquals(s, r);
        }
    }

    /**
     *
     * Method: dividedBy(Rational r)
     *
     */
    @Test
    public void testDividedBy() throws Exception {
        Rational r1 = null, r2 = null, r = null, s = null;
        long[] a1 = {4, -2, 5, -5, 0, 12, 16},
                b1 = {5, 5, 10, 3, 3, -5, 75},
                a2 = {1, 15, 10, -10, 125, 15, 30},
                b2 = {5, 15, -10, 9, 3, 4, 16},
                n = {4, -2, -1, 3, 0, -16, 128},
                d = {1, 5, 2, 2, 1, 25, 1125};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i], b1[i]);
            r2 = new Rational(a2[i], b2[i]);
            r = r1.dividedBy(r2);
            s = new Rational(n[i], d[i]);
            Assert.assertEquals(s, r);
        }
        try {
            r1 = new Rational(3, 1);
            r2 = new Rational(0, 11);
            r1.dividedBy(r2);
            Assert.fail("Exception expected");
        } catch (ArithmeticException e1) {}
        catch (Exception e) {
            Assert.fail("ArithmeticException expected");
        }
    }

    /**
     *
     * Method: mod(Rational r)
     *
     */
    @Test
    public void testMod() throws Exception {
        Rational r1 = null, r2 = null, r = null, s = null;
        long[] a1 = {4, -2, 5, -5, 0, 12, 16},
                a2 = {1, 15, 10, -10, 125, 15, 30};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i]);
            r2 = new Rational(a2[i]);
            r = r1.mod(r2);
            s = new Rational(a1[i] % a2[i]);
            Assert.assertEquals(s, r);
        }
        long[] a3 = {4, -2, 3, 5, -15, 0, 12},
                b3 = {1, 1, 2, 10, 3, 3, -5},
                a4 = {0, 0, 0, 10, -10, 125, 15},
                b4 = {5, 15, 1, -10, 9, 3, 4};
        for(int i = 0; i < a3.length; i++){
            try {
                r1 = new Rational(a3[i], b3[i]);
                r2 = new Rational(a4[i], b4[i]);
                r1.mod(r2);
                Assert.fail("Exception expected");
            } catch (ArithmeticException e1) {
                System.out.println("ArithmeticException caught: " + e1.getMessage());
            }
            catch (Exception e) {
                Assert.fail("ArithmeticException expected");
            }
        }
    }

    /**
     *
     * Method: factorial()
     *
     */
    @Test
    public void testFactorial() throws Exception {
        Rational r = null, s = null;
        long[] a = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};
        for(int i = 0; i < 11; i++){
            r = new Rational(i).factorial();
            s = new Rational(a[i]);
            Assert.assertEquals(s, r);
        }
        long[] a1 = {-4, -2, 3, 5, -15, -2},
                b1 = {1, 1, 2, 10, 4, 3};
        for(int i = 0; i < a1.length; i++){
            try {
                r = new Rational(a1[i], b1[i]);
                r.factorial();
                Assert.fail("Exception expected");
            } catch (ArithmeticException e1) {}
            catch (Exception e) {
                Assert.fail("ArithmeticException expected");
            }
        }
    }

    /**
     *
     * Method: negative()
     *
     */
    @Test
    public void testNegative() throws Exception {
        Rational r = null, s = null;
        long[] a = {2, 3, -4, -5, 6, 0},
                b = {1, 6, 2, 3, 2, 3};
        for(int i = 0; i < a.length; i++){
            r = new Rational(a[i], b[i]).negative();
            s = new Rational(-a[i], b[i]);
            Assert.assertEquals(r, s);
        }
    }

    /**
     *
     * Method: isZero()
     *
     */
    @Test
    public void testIsZero() throws Exception {
        Rational r =  new Rational(0, 1);
        Assert.assertTrue(r.isZero());
        r =  new Rational(3, 1);
        Assert.assertFalse(r.isZero());
        r =  new Rational(-33, 121);
        Assert.assertFalse(r.isZero());
    }

    /**
     *
     * Method: isInteger()
     *
     */
    @Test
    public void testIsInteger() throws Exception {
        Rational r = null;
        long[] a = {0, 3, 30, 5, 12, -4},
                b = {2, 1, 10, 3, -12, 6};
        boolean[] s = {true, true, true, false, true, false};
        for(int i = 0; i < a.length; i++){
            r =  new Rational(a[i], b[i]);
            Assert.assertEquals(s[i], r.isInteger());
        }
    }

    /**
     *
     * Method: isNegative()
     *
     */
    @Test
    public void testIsNegative() throws Exception {
        Rational r = null;
        long[] a = {0, 3, -30, 5, 12, -4},
                b = {2, 1, 10, 3, -12, -6};
        boolean[] s = {false, false, true, false, true, false};
        for(int i = 0; i < a.length; i++){
            r =  new Rational(a[i], b[i]);
            Assert.assertEquals(s[i], r.isNegative());
        }
    }

    /**
     *
     * Method: equals(Object obj)
     *
     */
    @Test
    public void testEquals() throws Exception {
        Rational r1 = null, r2 = null;
        long[] a1 = {12, -12, 5, -5, 0, -24},
                b1 = {15, 15, -10, -10, 3, -2},
                a2 = {4, 24, -1, -7, 0, 36},
                b2 = {5, -30, 2, -14, -5, 3};
        for(int i = 0; i < a1.length; i++){
            r1 = new Rational(a1[i], b1[i]);
            r2 = new Rational(a2[i], b2[i]);
            Assert.assertEquals(r1, r2);
        }
    }

    /**
     *
     * Method: toString()
     *
     */
    @Test
    public void testToString() throws Exception {
        Rational r = null;
        long[] a = {0, 3, -30, 5, 12, -4},
                b = {2, 1, 10, 3, -12, -6};
        String[] s = {"0", "3", "-3", "5/3", "-1", "2/3"};
        for(int i = 0; i < a.length; i++){
            r =  new Rational(a[i], b[i]);
            Assert.assertEquals(s[i], r.toString());
        }
    }

    /**
     *
     * Method: value()
     *
     */
    @Test
    public void testValue() throws Exception {
        Rational r = null;
        long[] a = {0, 3, -30, 5, 12, -4},
                b = {2, 1, 10, 3, -12, -6};
        double[] s = {0, 3, -3, 5.0/3, -1, 2.0/3};
        for(int i = 0; i < a.length; i++){
            r =  new Rational(a[i], b[i]);
            Assert.assertEquals(s[i], r.value(),1e-8);
        }
    }

    /**
     *
     * Method: getGCD(long a, long b)
     *
     */
    @Test
    public void testGetGCD() throws Exception {
        Method method = null;
        try {
            method = Rational.class.getDeclaredMethod("getGCD", long.class, long.class);
            method.setAccessible(true);
            long[] a = {48, 12, 45, 59, 0},
                    b = {32, 60, 56, 60, 3},
                    c = {16, 12, 1, 1, 3};
            long d;
            for(int i = 0; i < a.length; i++){
                d = (long) method.invoke(null, a[i], b[i]);
                Assert.assertEquals(c[i], d);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                method.setAccessible(false);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

} 
