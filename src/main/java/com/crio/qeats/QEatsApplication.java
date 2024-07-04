
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats;

import com.crio.qeats.globals.GlobalConstants;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Import SLF4J classes

@SpringBootApplication
@Log4j2
public class QEatsApplication {

    private static final Logger log = LoggerFactory.getLogger(QEatsApplication.class); // Initialize the log variable

    public static void main(String[] args) {
        SpringApplication.run(QEatsApplication.class, args);

        // Log message when server starts successfully
        log.info("Congrats! Your QEatsApplication server has started");
    }

    /**
     * Fetches a ModelMapper instance.
     *
     * @return ModelMapper
     */
    @Bean // Want a new obj every time
    @Scope("prototype")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}


