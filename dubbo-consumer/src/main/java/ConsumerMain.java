import com.hsh.hystrix.command.GetUserInfoCommand;
import com.hsh.hystrix.command.GetUserInfoObservableCommand;

import com.hsh.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConsumerMain {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerMain.class);

    public static void main(String[] args) throws Exception{

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF.spring/spring-resource.xml");
        context.start();

//        GetUserInfoCommand getUserInfoCommand = (GetUserInfoCommand)context.getBean("getUserInfoCommand"); // 获取远程服务代理
//        getUserInfoCommand.setUserId(1);
//
//        UserDTO userDTO1 = getUserInfoCommand.execute();
//        logger.info("获取到用户对象：{}", userDTO1);
//
//        Future<UserDTO> future = getUserInfoCommand.queue();
//        UserDTO userDTO2 = future.get(5000, TimeUnit.MILLISECONDS);
//        logger.info("获取到用户对象：{}", userDTO2);


        UserDTO userDTO3 = new UserDTO();
        final List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        GetUserInfoObservableCommand userInfoObservableCommand = (GetUserInfoObservableCommand)context.getBean("userInfoObservableCommand"); // 获取远程服务代理
        userInfoObservableCommand.setUserIdList(Arrays.asList(1,2,3));

        Observable observable= userInfoObservableCommand.observe();
        observable.subscribe(new Observer<UserDTO>() {
            public void onCompleted() {

                logger.info("查询完所有数据，list={}", userDTOList);
            }

            public void onError(Throwable e) {
                e.printStackTrace();
            }

            public void onNext(UserDTO userDTO) {
                userDTOList.add(userDTO);
            }
        });

        logger.info("获取到用户对象：{}", userDTO3);

    }
}
