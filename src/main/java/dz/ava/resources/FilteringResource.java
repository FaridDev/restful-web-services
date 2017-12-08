package dz.ava.resources;

import dz.ava.domaine.SomeBean;
import lombok.extern.slf4j.Slf4j;
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
}
