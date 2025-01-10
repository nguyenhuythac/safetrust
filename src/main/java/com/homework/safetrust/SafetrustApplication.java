package com.homework.safetrust;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
* 
* Safetrust Application
*
* @author Thac Nguyen
* 
*/
@SpringBootApplication
public class SafetrustApplication {
	public static void main(String[] args) {
		SpringApplication.run(SafetrustApplication.class, args);
	}

	/**
    * 
    * <p>ModelMapper bean</p>
    * @return ModelMapper
    *
    */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
