import com.alibaba.dubbo.container.Main;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException{

        //dubbo默认启动spring容器
        Main.main(null);
    }
}
