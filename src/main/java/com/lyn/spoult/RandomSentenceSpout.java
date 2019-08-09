package com.lyn.spoult;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;


/**
 * Spout类需要继承BaseRichSpout抽象类实现
 */
public class RandomSentenceSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String[] sentences = new String[]{"the cow jumped over the moon", "an apple a day keeps the doctor away",
            "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature"
    };

    /**
     * 初始化的一些操作放到这里
     *
     * @param conf           配置信息
     * @param context     应用的上下文
     * @param collector 向下游输出数据的收集器
     */
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 处理业务逻辑，在最后向下游输出数据,发射一个Tuple到Topology都是通过这个方法来实现的。
     */
    public void nextTuple() {
        //随机生成句子
        String sentence = this.sentences[new Random().nextInt(sentences.length)];
        System.out.println("生成的句子为 --> " + sentence);
        //向下游输出
        this.collector.emit(new Values(sentence));
    }

    /**
     * 此方法用于声明当前Spout的Tuple发送流的域名字。
     * @param declarer
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //定义向下游输出的名称
        declarer.declare(new Fields("sentence"));
    }

    /**
     *  此方法定义在BaseComponent类内，用于声明针对当前组件的特殊的Configuration配置
     * @return
     */
    @Override
    public Map<String, Object> getComponentConfiguration() {
        return super.getComponentConfiguration();
    }
}