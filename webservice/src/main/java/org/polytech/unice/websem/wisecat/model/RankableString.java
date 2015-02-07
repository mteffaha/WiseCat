package org.polytech.unice.websem.wisecat.model;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class RankableString {
    private String rankableID;
    private String name;

    public RankableString(String personID, String name) {
        this.name = name;
        this.rankableID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRankableID() {
        return rankableID;
    }

    public void setRankableID(String rankableID) {
        this.rankableID = rankableID;
    }
}
