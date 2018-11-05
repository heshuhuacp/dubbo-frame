import com.hsh.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class ConsumerMain {

    public static void main(String[] args) throws IOException{

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF.spring/spring-resource.xml");
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        long beginTime = System.currentTimeMillis();
        Map<String, Object> resultMap = demoService.sayHello("1"); // 执行远程方法
        long endTime = System.currentTimeMillis();
        System.out.println("consumer receiver:"+ resultMap.get("result")+",耗费时间：" + (endTime- beginTime)); // 显示调用结果
    }
}
