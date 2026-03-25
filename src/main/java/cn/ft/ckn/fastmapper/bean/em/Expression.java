package cn.ft.ckn.fastmapper.bean.em;

/**
 * @author ckn
 */
public enum Expression {
    Equal("equal"," = "),
    NotEqual("notEqual"," != "),
    Greater("greater"," > "),
    GreaterOrEqual("greaterOrEqual"," >= "),
    Less("less"," < "),
    LessOrEqual("lessOrEqual"," <= "),
    Like("like"," like "),
    NotLike("notLike"," NOT LIKE "),
    Between("between"," BETWEEN "),
    NotBetween("notBetween"," NOT BETWEEN "),
    IsNull("isNull"," IS NULL "),
    In("in"," IN "),
    NotIn("notIn"," NOT IN "),
    IsNotNull("isNotNull"," IS NOT NULL "),
    OrderBy("orderBy"," ORDER BY "),
    LeftBracket("leftBracket", " ( "),
    RightBracket("rightBracket", " ) "),
    Match(" MATCH", " AGAINST"),
    NotMatch(" NOT MATCH", " AGAINST"),
    LineSeparator("lineSeparator",System.lineSeparator());
    public String name;
    public String expression;

    Expression(String name, String expression) {
        this.name=name;
        this.expression = expression;
    }
}
