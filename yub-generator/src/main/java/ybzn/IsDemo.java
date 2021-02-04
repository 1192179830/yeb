package ybzn;

import java.util.Date;

/**
 * @author Hugo
 * @time 2021/1/21
 */

public class IsDemo {
    Integer userId;
    String userName;

    public IsDemo () {
    }

    public IsDemo (Integer userId, String userName, String issueContent, Integer issueId, String userAnswer, Integer issueType, Date collectFullDate, Date collectFullDeadline) {
        this.userId = userId;
        this.userName = userName;
        this.issueContent = issueContent;
        this.issueId = issueId;
        this.userAnswer = userAnswer;
        this.issueType = issueType;
        this.collectFullDate = collectFullDate;
        this.collectFullDeadline = collectFullDeadline;
    }

    @Override
    public String toString () {
        return "IsDemo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", issueContent='" + issueContent + '\'' +
                ", issueId=" + issueId +
                ", userAnswer='" + userAnswer + '\'' +
                ", issueType=" + issueType +
                ", collectFullDate=" + collectFullDate +
                ", collectFullDeadline=" + collectFullDeadline +
                '}';
    }

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getIssueContent () {
        return issueContent;
    }

    public void setIssueContent (String issueContent) {
        this.issueContent = issueContent;
    }

    public Integer getIssueId () {
        return issueId;
    }

    public void setIssueId (Integer issueId) {
        this.issueId = issueId;
    }

    public String getUserAnswer () {
        return userAnswer;
    }

    public void setUserAnswer (String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getIssueType () {
        return issueType;
    }

    public void setIssueType (Integer issueType) {
        this.issueType = issueType;
    }

    public Date getCollectFullDate () {
        return collectFullDate;
    }

    public void setCollectFullDate (Date collectFullDate) {
        this.collectFullDate = collectFullDate;
    }

    public Date getCollectFullDeadline () {
        return collectFullDeadline;
    }

    public void setCollectFullDeadline (Date collectFullDeadline) {
        this.collectFullDeadline = collectFullDeadline;
    }

    String issueContent;
    Integer issueId;
    String userAnswer;
    Integer issueType;
    Date collectFullDate;
    Date collectFullDeadline;
}

