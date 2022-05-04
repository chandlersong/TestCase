package org.example.producer;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboService;

@Log4j2
@DubboService(interfaceName = "helloWorld")
public class HelloServiceImpl implements HelloService {


    @Override
    publi