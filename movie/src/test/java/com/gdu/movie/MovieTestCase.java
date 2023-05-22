package com.gdu.movie;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.movie.mapper.MovieMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieTestCase {

	@Autowired
	private MovieMapper movieMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTestCase.class);
	
	@Test
	public void test() {
		
	}

}
