package com.phasec.plagsafe;

import javax.annotation.Resource;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.phasec.plagsafe.system.SystemStatisticsService;

@SpringBootApplication
public class PlagsafeApplication  implements CommandLineRunner{

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(PlagsafeApplication.class, args);

        // get an instance
        SystemStatisticsService stats = SystemStatisticsService.getSystemStatInstance();

        // load the stats
        stats.resetSystemStats();
	}
	
	/**
	 * this method deletes all the existing file in the upload directory
	 */
	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
	
	
	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
	    return new TomcatEmbeddedServletContainerFactory() {
	    	@Override
			protected void postProcessContext(Context context) {
	    		((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
	    	}
	    };
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
