package com.parkit.parkingsystem;

import com.parkit.parkingsystem.util.InputReaderUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class InputReaderUtilTest {
	
	private InputReaderUtil inputReaderUtil = new InputReaderUtil();
	
	@Test
	public void readSelectionWithACorrectEntry() {
		int underTest;
		String data = "1";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  underTest = inputReaderUtil.readSelection();
		} finally {
			  System.setIn(stdin);
			}
		assertEquals(1,underTest);		
	}
	
	@Test
	public void readSelectionWithAnIncorrectEntry() {
		int underTest;
		String data = "x";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  underTest = inputReaderUtil.readSelection();
		} finally {
			  System.setIn(stdin);
			}
		assertEquals(-1,underTest);		
	}
	
	@Test
	public void readVehicleRegistrationWithAnIncorrectEntry() throws Exception {

		assertThrows(IllegalArgumentException.class, () -> 
		{		
		String data = "\n";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  inputReaderUtil.readVehicleRegistrationNumber();
		} finally {
			  System.setIn(stdin);
			}
		});
	}
	
	@Test
	public void readVehicleRegistrationWithACorrectEntry() throws Exception {
		String underTest;
		String data = "ABCDEF";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  underTest = inputReaderUtil.readVehicleRegistrationNumber();
		} finally {
			  System.setIn(stdin);
			}
		assertEquals("ABCDEF",underTest);		
	}

}
