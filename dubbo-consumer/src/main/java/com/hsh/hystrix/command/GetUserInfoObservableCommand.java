package com.hsh.hystrix.command;

import com.hsh.model.UserDTO;
import com.hsh.service.UserService;
import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;

@Component("userInfoObservableCommand")
public class GetUserInfoObservableCommand extends HystrixObservableCommand<UserDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInfoObservableCommand.class);

    private List<Integer> userIdList;

    @Autowired
    private UserService userService;


    public GetUserInfoObservableCommand(){

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetUserInfoObservableGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetUserInfoCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000))
        );

    }


    @Override
    protected Observable<UserDTO> construct() {

        logger.info("当前执行线程，{}", Thread.currentThread().getName());

        return Observable.create(new OnSubscribe<UserDTO>(){
            public void call(Subscriber<? super UserDTO> subscriber) {

                try {
                    for (Integer userId: userIdList) {

                        UserDTO userDTO =  userService.getUserById(userId);
                        subscriber.onNext(userDTO);

                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io());
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    /**
     * 降级服务
     * @return
     */
    @Override
    protected Observable<UserDTO> resumeWithFallback() {

        logger.info("GetUserInfoObservableCommand调用降级服务。。。");
        return Observable.create(new OnSubscribe<UserDTO>(){
            public void call(Subscriber<? super UserDTO> subscriber) {

                try {
                    for (Integer userId: userIdList) {

                        UserDTO userDTO = new UserDTO();
                        subscriber.onNext(userDTO);

                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io());
    }
}
