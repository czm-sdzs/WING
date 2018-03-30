package wing.service.sample;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lakala.bigdata.drc.auc.api.sample.SampleDubboApi;
import org.springframework.stereotype.Component;

/**
 * Create By: CuiBo
 * Date: 2018/3/26 11:51
 * Description:
 */
@Component
public class SampleDubboService implements SampleDubboApi {

    @Reference(version = "1.0.0")
    SampleDubboApi sampleDubboApi;

    @Override
    public String testDubboServiceCall() {
        return sampleDubboApi.testDubboServiceCall();
    }


}
