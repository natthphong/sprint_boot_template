package th.co.erp.sme;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class Testadsa {



    @Test
    public void test(){
        List<String> s =  new ArrayList<>();

        List<Integer> o =  s.stream().map(e->Integer.valueOf(e)).toList();
        log.info("{}" , o);

    }
}
