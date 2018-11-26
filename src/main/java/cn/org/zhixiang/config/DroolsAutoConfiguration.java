package cn.org.zhixiang.config;

import cn.org.zhixiang.entity.Dog;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;


/**
 * d
 *
 * @author syj
 * CreateTime 2018/11/23
 * describe:
 */
@Configuration
public class DroolsAutoConfiguration {


    @Bean
    public KieSession kieSession() throws IOException {
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        String url = "http://10.0.20.135:8080/drools-wb/maven2/cn/org/zhixiang/drools-test/0.0.1/drools-test-0.0.1.jar";
        KieServices kieServices = KieServices.Factory.get();
        KieRepository kieRepository = kieServices.getRepository();
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        resource.setBasicAuthentication("enabled");
        resource.setPassword("admin");
        resource.setUsername("admin");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KieModule kieModule = kieRepository.addKieModule(kieServices.getResources().newInputStreamResource(is));
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieSession kieSession =kieContainer.newKieSession();
        return kieSession;
    }
    @Bean
    public Dog dog() throws IOException {
       return new Dog();
    }




}
