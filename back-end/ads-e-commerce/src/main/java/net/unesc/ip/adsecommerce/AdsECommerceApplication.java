package net.unesc.ip.adsecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdsECommerceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(AdsECommerceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdsECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("ADS E-COMMERCE IS ONLINE.");
    }
}
