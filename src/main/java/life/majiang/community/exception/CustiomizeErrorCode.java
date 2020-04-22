package life.majiang.community.exception;

public enum CustiomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找到问题不在,请尝试查询别的问题！"),
    TARFET_PARAM_NOT_FOUND(2002,"未选择任何问题或者评论进行回复！"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后进行!"),
    SYS_ERROR(2004,"服务器冒烟了！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在！");


    private Integer code;
    private String message;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustiomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }
}
