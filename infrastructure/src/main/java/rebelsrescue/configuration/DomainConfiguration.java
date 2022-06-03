package rebelsrescue.configuration;

import ddd.DomainService;
import ddd.Stub;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import rebelsrescue.fleet.Fleet;

@Configuration
@ComponentScan(
        basePackageClasses = {Fleet.class},
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, Stub.class} )})
public class DomainConfiguration {
}
