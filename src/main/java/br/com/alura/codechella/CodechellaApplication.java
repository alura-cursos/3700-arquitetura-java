package br.com.alura.codechella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class CodechellaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodechellaApplication.class, args);
	}

}
