package com.gdu.movie.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.movie.service.MovieService;

@Component
@EnableScheduling
public class FindComedyScheduler {

	@Autowired
	private MovieService movieService;
	
	@Scheduled(cron="0 * * * * *")
	
	public void execute() {
		movieService.genreComedy();
	}
}
