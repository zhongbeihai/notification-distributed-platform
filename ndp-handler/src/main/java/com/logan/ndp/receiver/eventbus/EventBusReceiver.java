//package com.logan.ndp.receiver.eventbus;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@ConditionalOnProperty(name = "ndp.mq.pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
//public class EventBusReceiver implements EventBusListener {
//
//    @Autowired
//    private ConsumeService consumeService;
//
//    @Override
//    @Subscribe
//    public void consume(List<TaskInfo> lists) {
//        consumeService.consume2Send(lists);
//
//    }
//
//    @Override
//    @Subscribe
//    public void recall(RecallTaskInfo recallTaskInfo) {
//        consumeService.consume2recall(recallTaskInfo);
//    }
//}
