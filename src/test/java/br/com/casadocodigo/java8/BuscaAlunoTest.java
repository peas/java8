package br.com.casadocodigo.java8;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.function.*;

/**
 * Unit test for simple App.
 */
public class BuscaAlunoTest 
{

    @Test
    public void testApp()
    {
        List lista = Arrays.asList(1,2,3);
        
		lista.forEach(new Consumer(){
   			public void accept(Object a) {
           		System.out.println(a);                  
   			}
		});

		lista.forEach((v) -> {System.out.println(v);});

        assertTrue( true );
    }
}
