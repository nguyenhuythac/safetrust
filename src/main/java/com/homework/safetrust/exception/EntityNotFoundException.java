package com.homework.safetrust.exception;
/**
* 
* Entity Not Found Exception class.
* 
* @author Thac Nguyen
*/
public class EntityNotFoundException extends Exception {
    /**
    * 
    * <p>Entity Not Found Exception constructor</p>
    * @param message exception message
    *
    */
    public EntityNotFoundException(String message){
        super(message);
    }
}
