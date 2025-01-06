package tracker.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Course {
    private final String name;
    private Integer score;
    private final Integer requiredScoreForCompletion;
    private Integer submissionCount;
    private Boolean isNotified;

    public Course(String name, Integer requiredScoreForCompletion) {
        this.name = name;
        this.score = 0;
        this.requiredScoreForCompletion = requiredScoreForCompletion;
        this.submissionCount = 0;
        this.isNotified = false;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void addScore(Integer addedScore) {

        if (addedScore != 0) {
            submissionCount++;
        }
        this.score += addedScore;
    }

    public Integer getSubmissionCount() {
        return this.submissionCount;
    }

    public Double getCompletionRate() {
        return BigDecimal.valueOf(this.score * 100.00 / this.requiredScoreForCompletion)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Boolean isNotified() {
        return isNotified;
    }

    public void markAsNotified() {
        this.isNotified = true;
    }

}
