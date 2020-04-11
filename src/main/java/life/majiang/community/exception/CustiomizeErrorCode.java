package life.majiang.community.exception;

public enum CustiomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("你找到问题不在,请尝试查询别的问题！");

    private String message;

    @Override
    public String getMessage(){
        return message;
    }

    CustiomizeErrorCode(String message){
        this.message = message;
    }
}
