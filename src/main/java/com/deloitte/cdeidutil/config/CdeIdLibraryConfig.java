package com.deloitte.cdeidutil.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.CrudRepository;
import com.deloitte.cdeidutil.entity.CdeId;
import com.deloitte.cdeidutil.utils.CdeIdUtil;

@Configuration
@Import({//
  RedisClientConfig.class
})
public class CdeIdLibraryConfig {
    @Bean
    @ConditionalOnClass(CrudRepository.class)
    public CdeIdUtil cdeIdUtil(CrudRepository<CdeId, String> repository) {
        return new CdeIdUtil(repository);
    }
}
