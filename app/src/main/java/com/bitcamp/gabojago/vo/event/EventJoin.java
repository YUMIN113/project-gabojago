package com.bitcamp.gabojago.vo.event;

public class EventJoin {

    private Integer joinNo;
    private Integer eventNo;
    private String id;

    @Override
    public String toString() {
        return "EventJoin{" +
                "joinNo=" + joinNo +
                ", eventNo=" + eventNo +
                ", id='" + id + '\'' +
                '}';
    }

    public Integer getJoinNo() {
        return joinNo;
    }

    public void setJoinNo(Integer joinNo) {
        this.joinNo = joinNo;
    }

    public Integer getEventNo() {
        return eventNo;
    }

    public void setEventNo(Integer eventNo) {
        this.eventNo = eventNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
