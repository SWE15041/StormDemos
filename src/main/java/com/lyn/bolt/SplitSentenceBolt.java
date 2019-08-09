package com.lyn.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 实现Bolt，需要继承BaseRichBolt
 */
public class SplitSentenceBolt extends BaseRichBolt {
    private OutputCollector collector;

    /**
     * 为Bolt提供了OutputCollector，用来从Bolt中发送Tuple
     * 注：Bolt中Tuple的发送可以在prepare方法中、execute方法中、cleanup等方法中进行，一般都是些在execute中。
     *
     * @param stormConf 当前计算机的集群配置
     * @param context   用于获取当前任务在拓扑中的上下文信息
     * @param collector 用于向下一个组件发送元组
     */
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 处理上游发送的单个元组
     *
     * @param tuple 上游的tuple
     */
    public void execute(Tuple tuple) {
        // 通过Tuple的getValueByField获取上游传递的数据，其中"sentence"是定义的字段名称
        String sentence = tuple.getStringByField("sentence");
        // 进行分割处理
        String[] words = sentence.split(" ");
        //向下游输出数据
        for (String word : words) {
            this.collector.emit(new Values(word));
        }
    }

    /**
     * 声明元组的输出模式
     *
     * @param declarer 用于声明输出流id，输出字段等
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
 