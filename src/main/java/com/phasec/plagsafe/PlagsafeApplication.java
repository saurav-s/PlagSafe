package com.phasec.plagsafe;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlagsafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlagsafeApplication.class, args);
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
}
