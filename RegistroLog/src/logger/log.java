package logger;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivonildo Da Silva
 */
public class log implements Serializable
{
    private String message;
    private final String  dateTime;
    private String comment;
    
    public log(String message,String comment)
    {
        this.message=message;
        this.dateTime=java.util.Calendar.getInstance().getTime().toString();
        this.comment=comment;
    }

    public String getDateTime()
    {
        return this.dateTime;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getLog()
    {
        return this.message+"\t"+this.dateTime+"\t"+this.comment;
    }
}
