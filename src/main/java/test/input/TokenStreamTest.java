package test.input; 

import arithmetic.*;
import input.TokenStream;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TokenStream Tester. 
* 
* @author <Authors name> 
* @since <pre>ÈýÔÂ 7, 2019</pre> 
* @version 1.0 
*/ 
public class TokenStreamTest {
@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getNextToken(boolean cursorMove) 
* 
*/ 
@Test
public void testGetNextToken() throws Exception { 
//TODO: Test goes here...
    String s = "44+55+99129";
    TokenStream t = new TokenStream(s);
    while(t.hasNextToken()){
        t.getNextToken(true);
    }
} 

/** 
* 
* Method: hasNextToken() 
* 
*/ 
@Test
public void testHasNextToken() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: isEmptyChar(char c) 
* 
*/ 
@Test
public void testIsEmptyChar() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = TokenStream.getClass().getMethod("isEmptyChar", char.class); 
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
* Method: isNumberChar(char c) 
* 
*/ 
@Test
public void testIsNumberChar() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = TokenStream.getClass().getMethod("isNumberChar", char.class); 
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
* Method: trimStart(String str) 
* 
*/ 
@Test
public void testTrimStart() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = TokenStream.getClass().getMethod("trimStart", String.class); 
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
* Method: nextNumber() 
* 
*/ 
@Test
public void testNextNumber() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = TokenStream.getClass().getMethod("nextNumber"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
