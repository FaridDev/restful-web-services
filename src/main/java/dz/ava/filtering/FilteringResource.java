package dz.ava.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class FilteringResource {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1","value2","value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBean(){
        return Arrays.asList(new SomeBean("value1","value2","value3"), new SomeBean("value11","value22","value33"));
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue dynamicFiltering(){
        SomeBean someBean = new SomeBean("value1","value2","value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue dynamicFilteringListOfSomeBean(){
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1","value2","value3"), new SomeBean("value11","value22","value33"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        mapping.setFilters(filters);

        return mapping;
    }
}
