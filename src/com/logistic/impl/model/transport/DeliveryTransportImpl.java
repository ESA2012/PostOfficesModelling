package com.logistic.impl.model.transport;

import com.logistic.api.model.post.PostOffice;
import com.logistic.api.model.transport.DeliveryTransport;
import com.logistic.impl.model.post.PostOfficeImproved;
import com.logistic.impl.service.DataStorage;
import com.logistic.impl.service.esa.routes.RouteMatrix;

import java.util.List;


public class DeliveryTransportImpl implements DeliveryTransportImproved {
    private PostOffice officeA;
    private PostOffice officeB;
    private Type type;
    private double price;
    private double range;
    private int time;


    public DeliveryTransportImpl (PostOffice officeA, PostOffice officeB, Type type) {
        this.officeA = officeA;
        this.officeB = officeB;
        this.type = type;
        range = officeA.getGeolocation().distance(officeB.getGeolocation());
        price = ((int)((getRange()*type.getCostPerMile())*100)) / 100d;
        time = (int)getRange()/type.getSpeed();
    }


    @Override
    public Type getType() {
        return type;
    }


    @Override
    public PostOffice getStartPostOffice() {
        return officeA;
    }


    @Override
    public PostOffice getDestinationPostOffice() {
        return officeB;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public double getRange() {
        return range;
    }


    public String toString() {
        return officeA.getCode()
                + " -> " + officeB.getCode()
                + " : Range=" + getRange()
                + "; Time="+ getTime()
                + "; Type=" + getType()
                + "; Cost=" + getPrice();
    }
}
