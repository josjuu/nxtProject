package Exceptions;

public class DirectionNotFoundException extends Exception{
    public DirectionNotFoundException() {}

    public DirectionNotFoundException(String message)
    {
       super(message);
    }
}
