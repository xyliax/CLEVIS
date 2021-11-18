package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.Application;
import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 */
public class TestClevis {
	private Application app;
	private Clevis clevis;

	/**
	 *
	 */
	@Before
	public void prepare() {
		app = new Application(new String[]{"-html", "log.html", "-txt", "log.txt"});
		clevis = app.getController();
	}

	/**
	 *
	 */
	@Test
	public void testClevis1() {
		try {
			app.getClevisIO().setIn(new FileInputStream("test_input_1.txt"));
			app.run();
			assert true;
		} catch (FileNotFoundException e) {
			assert false;
		}
	}

	/**
	 *
	 */
	@Test
	public void testClevis2() {
		try {
			app.getClevisIO().setIn(new FileInputStream("test_input_2.txt"));
			app.run();
			assert true;
		} catch (FileNotFoundException e) {
			assert false;
		}
	}

	/**
	 *
	 */
	@Test
	public void testClevis3() {
		try {
			app.getClevisIO().setIn(new FileInputStream("test_input_3.txt"));
			app.run();
			assert true;
		} catch (FileNotFoundException e) {
			assert false;
		}
	}
}
