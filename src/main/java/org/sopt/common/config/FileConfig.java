package org.sopt.common.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {

	@Bean
	public Path memberDataFilePath() {
		return Path.of("../data/member/members.csv");
	}
}
