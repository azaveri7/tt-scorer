package model;

public final class MatchResult {

    public MatchResult(ResultType resultType) {
        this.resultType = resultType;
    }

    public enum ResultType {WON, TIED, DRAWN, NO_RESULT, ABANDONED, AWARDED, CONCEDED}

    private final ResultType resultType;

    public ResultType getResultType(){
        return resultType;
    }
}
