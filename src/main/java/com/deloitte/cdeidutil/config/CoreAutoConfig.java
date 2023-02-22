package com.deloitte.cdeidutil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({//
    CdeIdLibraryConfig.class
})
public class CoreAutoConfig {

}
