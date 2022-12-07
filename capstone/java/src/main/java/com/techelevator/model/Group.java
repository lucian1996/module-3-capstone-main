package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int groupId;
    private String groupName;
    private List<Integer> users = new ArrayList<>();
    private String groupDescription;
    private int groupOwnerId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public int getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(int groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", users=" + users +
                ", groupDescription='" + groupDescription + '\'' +
                ", groupOwnerId=" + groupOwnerId +
                '}';
    }
}
