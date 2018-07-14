package com.hqjy.tiku.task.webservice;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "sendTeacherList",
        propOrder = {"string", "string1", "_int", "int1"}
)
@XmlRootElement(
        name = "sendTeacherList"
)
public class SendTeacherList {
    @XmlElement(
            name = "string"
    )
    protected String string;
    @XmlElement(
            name = "string1"
    )
    protected String string1;
    @XmlElement(
            name = "int"
    )
    protected Integer _int;
    @XmlElement(
            name = "int1"
    )
    protected Integer int1;

    public SendTeacherList() {
    }

    public String getString() {
        return this.string;
    }

    public void setString(String value) {
        this.string = value;
    }

    public String getString1() {
        return this.string1;
    }

    public void setString1(String value) {
        this.string1 = value;
    }

    public Integer getInt() {
        return this._int;
    }

    public void setInt(Integer value) {
        this._int = value;
    }

    public Integer getInt1() {
        return this.int1;
    }

    public void setInt1(Integer value) {
        this.int1 = value;
    }
}
