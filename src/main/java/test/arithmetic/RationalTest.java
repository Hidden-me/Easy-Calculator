package test.arithmetic; 

import java.lang.reflect.*;
import arithmetic.Rational;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Rational Tester. 
* 
* @author <Authors name> 
* @since <pre>ÈýÔÂ 6, 2019</pre> 
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
* Method: add(Rational r) 
* 
*/ 
@Test
public void testAdd() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(1, 2);
    Rational b = new Rational(2, 3);
    Rational c = new Rational(7, 6);
    Assert.assertEquals(c, a.add(b));
} 

/** 
* 
* Method: subtract(Rational r) 
* 
*/ 
@Test
public void testsubtract() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(1, 2);
    Rational b = new Rational(2, 3);
    Rational c = new Rational(-1, 6);
    Assert.assertEquals(c, a.subtract(b));
} 

/** 
* 
* Method: multiply(Rational r) 
* 
*/ 
@Test
public void testMultiply() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(1, 2);
    Rational b = new Rational(2, 3);
    Rational c = new Rational(1, 3);
    Assert.assertEquals(c, a.multiply(b));
} 

/** 
* 
* Method: dividedBy(Rational r) 
* 
*/ 
@Test
public void testDividedBy() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(1, 2);
    Rational b = new Rational(2, 3);
    Rational c = new Rational(3, 4);
    Assert.assertEquals(c, a.dividedBy(b));
} 

/** 
* 
* Method: isZero() 
* 
*/ 
@Test
public void testIsZero() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(3, 2);
    Rational b = new Rational(-24, 16);
    Rational c = new Rational(-24, -16);
    Assert.assertTrue(a.add(b).isZero());
    Assert.assertTrue(a.subtract(c).isZero());
    Assert.assertFalse(a.subtract(b).isZero());
}

/**
*
* Method: equals(Object obj)
*
*/
@Test
public void testEquals() throws Exception {
//TODO: Test goes here...
    Rational a = new Rational(1, 2);
    Rational b = new Rational(1, 2);
    Rational c = new Rational(3, 2);
    Rational d = new Rational(3, 6);
    Assert.assertEquals(a, a);
    Assert.assertEquals(a, b);
    Assert.assertEquals(b, a);
    Assert.assertNotEquals(b, c);
    Assert.assertEquals(b, d);
}


    /**
     *
     * Method: value()
     *
     */
    @Test
    public void testvalue() throws Exception {
//TODO: Test goes here...
        Rational a = new Rational(1, 2);
        Rational b = new Rational(18, 12);
        Rational c = new Rational(6, 1);
        Rational d = new Rational(-6, 12);
        Rational e = new Rational(0, -12);
        Assert.assertEquals(0.5, a.value(), 1e-6);
        Assert.assertEquals(1.5, b.value(), 1e-6);
        Assert.assertEquals(6, c.value(), 1e-6);
        Assert.assertEquals(-0.5, d.value(), 1e-6);
        Assert.assertEquals(0, e.value(), 1e-6);
    }

    /**
* 
* Method: processSign() 
* 
*/ 
@Test
public void testProcessSign() throws Exception { 
//TODO: Test goes here... 
/*
    try {
        Method method = Rational.class.getMethod("processSign");
        method.setAccessible(true);
        Rational a = new Rational(-3, 5);
        method.invoke(a);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
*/
} 

/** 
* 
* Method: doReduction() 
* 
*/ 
@Test
public void testDoReduction() throws Exception { 
//TODO: Test goes here... 
/*
try { 
   Method method = Rational.class.getMethod("doReduction");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
} 

/** 
* 
* Method: standardize() 
* 
*/ 
@Test
public void testStandardize() throws Exception { 
//TODO: Test goes here...
    Rational a = new Rational(6, 9);
    Rational b = new Rational(8, 12);
    Rational c = new Rational(-12, 9);
    Rational d = new Rational(16, -12);
    Rational e = new Rational(54, -9);
    Rational f = new Rational(-6);
    Assert.assertEquals(a, b);
    Assert.assertEquals(c, d);
    Assert.assertEquals(e, f);
    /*
    try {
       Method method = Rational.getClass().getMethod("standardize");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
} 

} 
