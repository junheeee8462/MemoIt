package Jun.Project.MemoIt.model;

import java.util.Date;

public class Memo {
    private int id;
    private Long memberId;  
    private String content;
    private Date createdAt;

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for memberId
    public Long getMemberId() { 
        return memberId;
    }

    public void setMemberId(Long long1) {
        this.memberId = long1;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and Setter for createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
