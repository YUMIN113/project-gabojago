package com.bitcamp.gabojago.vo.event;

import java.util.List;

public class EventItem {

    private Integer itemNo;
    private String name;
    private Integer stock;
    private Integer eventNo;
    private Integer ranking;

    private EventAttachedFile eventAttachedFile;

    @Override
    public String toString() {
        return "EventItem{" +
                "itemNo=" + itemNo +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", eventNo=" + eventNo +
                ", ranking=" + ranking +
                ", eventAttachedFile=" + eventAttachedFile +
                '}';
    }
    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getEventNo() {
        return eventNo;
    }

    public void setEventNo(Integer eventNo) {
        this.eventNo = eventNo;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public EventAttachedFile getEventAttachedFile() {
        return eventAttachedFile;
    }

    public void setEventAttachedFile(EventAttachedFile eventAttachedFile) {
        this.eventAttachedFile = eventAttachedFile;
    }
}
