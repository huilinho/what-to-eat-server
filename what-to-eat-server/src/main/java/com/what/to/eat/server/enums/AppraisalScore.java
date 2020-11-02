package com.what.to.eat.server.enums;

public enum AppraisalScore {
    GOODAPPRAISAL(1, 2,"好评"),
    MIDDLEAPPRAISAL(2, -1,"中评"),
    BADAPPRAISAL(3, -3,"差评");

    private int value;
    private int score;
    private String name;

    public int getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    AppraisalScore(int value, int score, String name) {
        this.value = value;
        this.score = score;
        this.name = name;
    }

    public static AppraisalScore getByValue(int value) {
        for (AppraisalScore appraisalScore : values()) {
            if (appraisalScore.getValue() == value) {
                return appraisalScore;
            }
        }
        return null;
    }

    public static int matchScore(int value) {
        AppraisalScore appraisalScore = getByValue(value);
        if (appraisalScore == null){
            return 0;
        }
        switch (appraisalScore) {
            default:
                return 0;
            case GOODAPPRAISAL:
                return GOODAPPRAISAL.getScore();
            case BADAPPRAISAL:
                return BADAPPRAISAL.getScore();
            case MIDDLEAPPRAISAL:
                return MIDDLEAPPRAISAL.getScore();
        }
    }
}
