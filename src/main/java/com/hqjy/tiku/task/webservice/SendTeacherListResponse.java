package com.hqjy.tiku.task.webservice;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "sendTeacherListResponse",
        propOrder = {"_return"}
)
@XmlRootElement(
        namespace = "http://stuArrang.webservice.hq.itf.nc/ITeacherStudent",
        name = "sendTeacherListResponse"
)
public class SendTeacherListResponse {

    @XmlElement(
            name = "return"
    )
    protected String _return;

    public SendTeacherListResponse() {
    }

    public String getReturn() {
        return this._return;
    }

    public void setReturn(String value) {
        this._return = value;
    }
}