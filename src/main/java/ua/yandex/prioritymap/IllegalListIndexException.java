package ua.yandex.collections;

/**
 *
 * @author Кирилл
 */
public class IllegalListIndexException extends RuntimeException{
    IllegalListIndexException(final String message){
        super(message);
    }
}
