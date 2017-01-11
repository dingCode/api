package com.knowlegene.apirequest.hongkaitest.hongkaitest;

import com.knowlegene.apirequest.hongkaitest.hongkaitest.model.HongkaitestApi;
import com.rop.AbstractRopRequest;
import com.rop.annotation.IgnoreSign;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by hongkai on 2016/12/24.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hongkaitestAppiRequest")
public class HongkaitestAppiRequest extends AbstractRopRequest {

    @XmlAttribute
    private String travelPage;

    public String getTravelPage() {
        return travelPage;
    }

    public void setTravelPage(String travelPage) {
        this.travelPage = travelPage;
    }

}