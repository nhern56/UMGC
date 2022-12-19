class Negation: public SubExpression
{
public:
    Negation(Expression* left): SubExpression(left)
    {
    }
    int evaluate()
    {
        return !left->evaluate();
    }
};
